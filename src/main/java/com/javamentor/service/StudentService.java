package com.javamentor.service;

import com.javamentor.domain.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class StudentService {

    private final RestTemplate restTemplate;

    @Value("${rest.host}")
    private String URL;

    @Autowired
    public StudentService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    Logger LOGGER = Logger.getLogger(StudentService.class.getName());

    public List<Student> getAll() {
        try {
            ResponseEntity<List<Student>> response =
                    restTemplate.exchange(URL + "/api/admin/student/students",
                            HttpMethod.GET, null, new ParameterizedTypeReference<List<Student>>() {
                            });
            if (response.getStatusCode().equals(HttpStatus.OK)) {
                return response.getBody();
            }
        } catch (HttpClientErrorException e) {
            LOGGER.log(Level.WARNING, e.getResponseBodyAsString());
        }
        return Collections.emptyList();
    }
}