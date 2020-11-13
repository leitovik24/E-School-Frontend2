package com.javamentor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthService {

    @Value("${rest.host}")
    private String url;

    private final RestTemplate restTemplate;

    @Autowired
    public AuthService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    /**
     * Метод для проверки доступности роли для токена
     * @param token - JWT token для проверки
     * @param roles - Роли для проверки
     */
    public boolean checkTokenAndRole(String token, String roles){

        HttpHeaders headers = new HttpHeaders();
        Map<String, String> tokenAndUrl = new HashMap<>();
        tokenAndUrl.put("token", token);
        tokenAndUrl.put("roles", roles);
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Map<String, String>> requestBody = new HttpEntity<>(tokenAndUrl, headers);
        try {
            boolean result = restTemplate.postForEntity(url + "/api/auth/check-token", requestBody, Boolean.class).getBody();
            return result;
        } catch (HttpStatusCodeException exception) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getResponseBodyAsString());
        }
        return false;

    }
}
