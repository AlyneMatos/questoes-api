package com.example.vestibular.service;

import com.example.vestibular.model.*;
import com.example.vestibular.repository.QuestaoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
public class QuestaoServiceTest {

    @Autowired
    private QuestaoService questaoService;

    @MockBean
    private QuestaoRepository questaoRepository;

//    @Test
//    public boolean ckeck(){
//        Questao q = new Questao("enunciado",new Alternativa("a","b","c","d"),"b",new ConteudoQuestao(Disciplina.HISTORIA,"idade antiga","facil"),new Prova("enem","2020"));
//
//    }
}
