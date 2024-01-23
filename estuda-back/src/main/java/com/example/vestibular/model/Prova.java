package com.example.vestibular.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "provas")
public class Prova extends BaseEntity<Long>{


    private String banca;


    private String ano;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "prova")
    private Set<Questao> questoes;

    public Prova(String banca, String ano) {
        this.banca = banca;
        this.ano = ano;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Prova prova)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(banca, prova.banca) && Objects.equals(ano, prova.ano) && Objects.equals(questoes, prova.questoes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), banca, ano, questoes);
    }
}
