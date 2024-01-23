package com.example.vestibular.controller;

import com.example.vestibular.VestibularApplication;
import com.example.vestibular.model.Questao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.GsonTester;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = VestibularApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class QuestaoControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    private static String responsePath = "src/test/java/com/example/vestibular/controller/responseJson";

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void find() throws Exception{
        String jsonString = new String(Files.readAllBytes(Paths.get(responsePath+"/"+"findQuestao.json")));
        mockMvc.perform(get("/questoes/1"))
                .andExpect(status().isOk()).andExpect(content().json(jsonString));
    }

//    @Test
//    public  void saveTest() throws Exception{
//
//        String questaoJson = new String(Files.readAllBytes(Paths.get(responsePath+"/"+"saveQuestao.json")));
//
//        mockMvc.perform(post("/questoes"))
//                .content(questaoJson)
//                .andExpect(status().isCreated());
//    }
}
