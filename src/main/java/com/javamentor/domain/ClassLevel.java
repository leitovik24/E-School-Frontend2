package com.javamentor.domain;

public class ClassLevel {

    private Long id;
    private String numberClass;

    public ClassLevel(Long id, String numberClass) {
        this.id = id;
        this.numberClass = numberClass;
    }

    public ClassLevel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumberClass() {
        return numberClass;
    }

    public void setNumberClass(String numberClass) {
        this.numberClass = numberClass;
    }
}
