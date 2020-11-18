package com.javamentor.domain;

public class ClassLevel {

    private Long id;
    private NumberClass numberClass;

    public ClassLevel(Long id, NumberClass numberClass) {
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

    public NumberClass getNumberClass() {
        return numberClass;
    }

    public void setNumberClass(NumberClass numberClass) {
        this.numberClass = numberClass;
    }
}
