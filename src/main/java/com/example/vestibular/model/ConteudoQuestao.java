package com.example.vestibular.model;


import com.example.vestibular.model.enums.Disciplina;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class ConteudoQuestao {

    @Enumerated(EnumType.STRING)
    private Disciplina disciplina;

    private String assunto;

    private String dificuldade;
}
