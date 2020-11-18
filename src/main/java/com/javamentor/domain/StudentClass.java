package com.javamentor.domain;

import java.util.Set;

public class StudentClass {

    private Long id;
    private ClassLevel classLevel;
    private String symbolClass;
    private Set<Teacher> curators;

    public StudentClass(){

    }

    public StudentClass(Long id, ClassLevel classLevel, String symbolClass, Set<Teacher> curators) {
        this.id = id;
        this.classLevel = classLevel;
        this.symbolClass = symbolClass;
        this.curators = curators;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ClassLevel getClassLevel() {
        return classLevel;
    }

    public void setClassLevel(ClassLevel classLevel) {
        this.classLevel = classLevel;
    }

    public String getSymbolClass() {
        return symbolClass;
    }

    public void setSymbolClass(String symbolClass) {
        this.symbolClass = symbolClass;
    }

    public Set<Teacher> getCurators() {
        return curators;
    }

    public void setCurators(Set<Teacher> curators) {
        this.curators = curators;
    }
}
