package com.example.vestibular.models;


import com.example.vestibular.models.enums.KnowledgeArea;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
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
@Table(name = "disciplines")
public class Discipline extends BaseEntity<Long>{

    private String description;

    @Enumerated(EnumType.STRING)
    private KnowledgeArea knowledgeArea;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "discipline")
    private Set<Question> questions;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "discipline")
    private Set<Topic> topics;

    public Discipline(String description, KnowledgeArea knowledgeArea) {
        this.description = description;
        this.knowledgeArea = knowledgeArea;
    }
}
