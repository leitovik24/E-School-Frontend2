package com.javamentor.service;

import com.javamentor.dto.UserLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class LoginService {

    private final RestTemplate restTemplate;

    private static final String LOGIN_CONTROLLER = "http://localhost:8080/api/auth/login";

    @Autowired
    public LoginService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void sendLogin(String username, String password, Boolean rememberMe) {

        UserLogin userLogin = new UserLogin(username, password, rememberMe);

        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<UserLogin> requestBody = new HttpEntity<>(userLogin, headers);

        restTemplate.postForObject(LOGIN_CONTROLLER, requestBody, UserLogin.class);
    }
}
