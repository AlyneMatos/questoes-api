package com.example.vestibular.config;

import com.example.vestibular.controllers.AuthenticationController;
import com.example.vestibular.models.*;
import com.example.vestibular.models.enums.KnowledgeArea;
import com.example.vestibular.models.enums.Level;
import com.example.vestibular.models.enums.UserRoles;
import com.example.vestibular.models.user.RegisterDTO;
import com.example.vestibular.repositories.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
public class LoadDatabase implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);
    private final QuestionRepository questionRepository;
    private final TestRepository testRepository;

    private final TopicRepository topicRepository;

    private final AlternativeRepository alternativeRepository;

    private final DisciplineRepository disciplineRepository;
    private final UserRepository userRepository;
    private final AuthenticationController authenticationController;

    public LoadDatabase(TestRepository testRepository,
                        TopicRepository topicRepository,
                        UserRepository userRepository,
                        AuthenticationController authenticationController,
                        AlternativeRepository alternativeRepository,
                        QuestionRepository questionRepository,
                        DisciplineRepository disciplineRepository
                       ) {

        this.questionRepository = questionRepository;
        this.testRepository = testRepository;
        this.userRepository = userRepository;
        this.authenticationController = authenticationController;
        this.topicRepository = topicRepository;
        this.disciplineRepository = disciplineRepository;
        this.alternativeRepository = alternativeRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        initQuestionData();
        initUserData();
    }

    private void initUserData() {
        RegisterDTO user = new RegisterDTO("admin","123456", UserRoles.ADMIN);
        authenticationController.register(user);
    }

    private void initQuestionData() {

        Test test = new Test("UECE", "2019.2 1ª Fase");
        testRepository.save(test);

        Discipline discipline = new Discipline("Historia", KnowledgeArea.CIENCIAS_HUMANAS);
        disciplineRepository.save(discipline);

        Topic topic = new Topic("Idade Média",discipline);
        topicRepository.save(topic);

        Question question = new Question("Na Itália, após as eleições" +
                "de 1994, o governo foi presidido por", "a",Level.FACIL,test,discipline);
        questionRepository.save(question);
        Alternative alternativeA = new Alternative("a","Romano Prodi",true,question);
        Alternative alternativeB = new Alternative("b","Giovanni Agnell",false,question);
        Alternative alternativeC = new Alternative("c","Silvio Berlusconi",false,question);
        Alternative alternativeD = new Alternative("d","Giuliano Amato",false,question);
        alternativeRepository.save(alternativeA);
        alternativeRepository.save(alternativeB);
        alternativeRepository.save(alternativeC);
        alternativeRepository.save(alternativeD);


//
//        Discipline topic = new Discipline(com.example.vestibular.models.enums.Discipline.HISTORIA,"idade media");
//        topicRepository.save(topic);
//
//        Discipline topic1 = new Discipline(com.example.vestibular.models.enums.Discipline.HISTORIA,"idade moderna");
//        topicRepository.save(topic);
//
//
//        Question question1 = new Question("A independência de Moçambique ocorreu em 1975, após um " +
//                "longo processo que começou com a organização da FRELIMO " +
//                "(Frente de Libertação de Moçambique), um movimento político nacionalista que " +
//                "foi fundado em 25 de junho de 1962, com o objetivo de " +
//                "lutar pela libertação do domínio colonial", "a",
//                new Alternative("português","inglês","francês","alemão"), Level.Fácil,test,topic);

    }
}
