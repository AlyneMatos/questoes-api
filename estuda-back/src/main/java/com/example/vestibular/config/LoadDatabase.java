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

        Prova prova = new Prova("UECE", "2019.2 1ª Fase");
        provaRepository.save(prova);

        Questao questao = new Questao("Na Itália, após as eleições" +
                "de 1994, o governo foi presidido por", "a",new Alternativa("Romano Prodi","Giovanni Agnell","Silvio Berlusconi","Giuliano Amato"),new ConteudoQuestao(Disciplina.HISTORIA,"Geral","facil"), prova);
        Questao questao1 = new Questao("A independência de Moçambique ocorreu em 1975, após um " +
                "longo processo que começou com a organização da FRELIMO " +
                "(Frente de Libertação de Moçambique), um movimento político nacionalista que " +
                "foi fundado em 25 de junho de 1962, com o objetivo de " +
                "lutar pela libertação do domínio colonial", "a",
                new Alternativa("português","inglês","francês","alemão"),new ConteudoQuestao(Disciplina.HISTORIA,"idade media","facil"), prova);

        questaoRepository.save(questao);
        questaoRepository.save(questao1);

        log.info("Preloading " + prova);

        log.info("Preloading " + questao);
    }
}
