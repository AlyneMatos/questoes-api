package com.example.vestibular.models.enums;

public enum KnowledgeArea {

    CIENCIA_DA_NATUREZA("Ciência da Natureza"),

    CIENCIAS_HUMANAS("Ciências Humanas"),
    MATEMATICA("Matemática"),
    LINGUAGENS("Linguagens e códigos");

    private String area;

    KnowledgeArea(String area) {
        this.area = area;
    }

    public String getArea() {
        return area;
    }
}
