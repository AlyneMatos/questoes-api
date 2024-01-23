package com.example.vestibular.config;

import com.example.vestibular.model.*;
import com.example.vestibular.model.enums.Disciplina;
import com.example.vestibular.repository.ProvaRepository;
import com.example.vestibular.repository.QuestaoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
public class LoadDatabase implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);
    private final QuestaoRepository questaoRepository;
    private final ProvaRepository provaRepository;

    public LoadDatabase(QuestaoRepository questaoRepository,
                        ProvaRepository provaRepository
                       ) {

        this.questaoRepository = questaoRepository;
        this.provaRepository = provaRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        initQuestaoData();
        initProvaData();
    }

    private void initProvaData() {
        log.info("Preloading " + provaRepository.save(new Prova("enem","2020")));
    }

    private void initQuestaoData() {

        Prova prova = new Prova("uece", "2022");
        provaRepository.save(prova);

        Questao questao = new Questao("enunciado", "a",new Alternativa("a","b","c","d"),new ConteudoQuestao(Disciplina.HISTORIA,"idade media","facil"), prova);
        Questao questao1 = new Questao("enunciado1", "a",new Alternativa("a","b","c","d"),new ConteudoQuestao(Disciplina.HISTORIA,"idade media","facil"), prova);

//        Questao questao = new Questao("enunciado", "a");

        questaoRepository.save(questao);
        questaoRepository.save(questao1);

        log.info("Preloading " + prova);

        log.info("Preloading " + questao);
    }
}
