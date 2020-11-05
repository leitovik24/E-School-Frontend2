package com.javamentor.domain;

import java.time.LocalDateTime;

public class Teacher extends User {

    public Teacher(String email, String firstName, String lastName, String password, Boolean enabled, LocalDateTime registrationDate) {
        super(email, firstName, lastName, password, enabled, registrationDate);
    }

    public Teacher() {
    }
}
