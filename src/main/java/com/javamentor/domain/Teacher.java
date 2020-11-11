package com.javamentor.domain;

import java.time.LocalDate;

public class Teacher extends User {

    public Teacher(String email, String firstName, String lastName, String password, Boolean enabled, LocalDate registrationDate) {
        super(email, firstName, lastName, password, enabled, registrationDate);
    }

    public Teacher() {
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "'" + email + '\'' +
                "'" + firstName + '\'' +
                "'" + lastName + '\'' +
                '}';
    }
}
