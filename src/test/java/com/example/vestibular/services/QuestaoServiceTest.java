package com.example.vestibular.services;

import com.example.vestibular.repositories.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class QuestaoServiceTest {

    @Autowired
    private QuestionService questaoService;

    @MockBean
    private QuestionRepository questaoRepository;
}
