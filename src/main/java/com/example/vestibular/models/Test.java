package com.example.vestibular.models;

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
@Table(name = "tests")
public class Test extends BaseEntity<Long>{

    @NotBlank
    private String institution;

    @NotBlank
    private String year;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "test")
    private Set<Question> questions;

    public Test(String institution, String year) {
        this.institution = institution;
        this.year = year;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Test test = (Test) o;
        return Objects.equals(institution, test.institution) && Objects.equals(year, test.year) && Objects.equals(questions, test.questions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), institution, year, questions);
    }
}
