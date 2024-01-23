package com.example.vestibular.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "questoes")
public class Questao extends BaseEntity<Long>{

    @NotBlank
    private String enunciado;

    private String itemCorreto;

    @Embedded
    private Alternativa alternativas;

    @Embedded
    private ConteudoQuestao conteudoQuestao;

    @ManyToOne
    private Prova prova;
}
