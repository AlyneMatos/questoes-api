package com.example.vestibular.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Embeddable
public class Alternativa {
    private String a;
    private String b;
    private String c;
    private String d;

    public Alternativa() {
    }
}
