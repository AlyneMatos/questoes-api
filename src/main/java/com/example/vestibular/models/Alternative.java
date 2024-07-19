package com.example.vestibular.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "alternatives")
public class Alternative extends BaseEntity<Long>{

    private String alternative;
    private String text;
    private Boolean isCorrect;

    @JsonBackReference
    @ManyToOne
    private Question question;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Alternative that = (Alternative) o;
        return Objects.equals(alternative, that.alternative) && Objects.equals(text, that.text) && Objects.equals(isCorrect, that.isCorrect) && Objects.equals(question, that.question);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), alternative, text, isCorrect, question);
    }
}
