package com.javamentor.service;

import com.javamentor.dto.model.UserLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

@Service
public class LoginService {

    private final RestTemplate restTemplate;

    @Value("${login}")
    private String login_url;

    @Autowired
    public LoginService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseEntity<?> sendLogin(String name, String password, Boolean rememberMe) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        UserLogin userLogin = new UserLogin(name, password, rememberMe);
        HttpEntity<UserLogin> requestBody = new HttpEntity<>(userLogin, headers);
        try {
            return restTemplate.postForEntity(login_url, requestBody, UserLogin.class);
        } catch (HttpStatusCodeException exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getResponseBodyAsString());
        }
    }
}
