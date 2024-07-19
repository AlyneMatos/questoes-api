package com.example.vestibular.models;

import com.example.vestibular.models.enums.Level;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Table(name = "questions")
public class Question extends BaseEntity<Long>{

    @NotBlank
    @Column(columnDefinition = "TEXT")
    private String statement;

    private String correctItem;

    @Enumerated(EnumType.STRING)
    private Level level;

    @ManyToOne
    private Test test;

    @ManyToOne
    private Discipline discipline;

    @JsonManagedReference
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Alternative> alternatives;

    public Question(String statement, String correctItem, Level level, Test test, Discipline discipline) {
        this.statement = statement;
        this.correctItem = correctItem;
        this.level = level;
        this.test = test;
        this.discipline = discipline;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Question question = (Question) o;
        return Objects.equals(statement, question.statement) && Objects.equals(correctItem, question.correctItem) && level == question.level && Objects.equals(test, question.test) && Objects.equals(discipline, question.discipline) && Objects.equals(alternatives, question.alternatives);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), statement, correctItem, level, test, discipline, alternatives);
    }

}
