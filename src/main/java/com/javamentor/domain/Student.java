package com.javamentor.domain;

import java.time.LocalDateTime;

public class Student extends User {

    public Student(String email, String firstName, String lastName, String password, Boolean enabled, LocalDateTime registrationDate) {
        super(email, firstName, lastName, password, enabled, registrationDate);
    }

    public Student() {

    }
}