package com.javamentor.domain;

import java.time.LocalDate;

public class Student extends User {

    public Student(String email, String firstName, String lastName, String password, Boolean enabled, LocalDate registrationDate) {
        super(email, firstName, lastName, password, enabled, registrationDate);
    }

    public Student() {
    }

    @Override
    public String toString() {
        return "Student{" +
                "'" + email + '\'' +
                "'" + firstName + '\'' +
                "'" + lastName + '\'' +
                '}';
    }
}