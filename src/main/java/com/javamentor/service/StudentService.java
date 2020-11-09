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
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class StudentService{

    private final RestTemplate restTemplate;

    @Value("${rest.host}/api/admin/student/students")
    private String URL;

    public StudentService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    Logger LOGGER = Logger.getLogger(StudentService.class.getName());

    public List<Student> getAll(String filter) {
        try {
            ResponseEntity<List<Student>> response =
                    restTemplate.exchange(URL,
                            HttpMethod.GET, null, new ParameterizedTypeReference<List<Student>>() {
                            });
            if (response.getStatusCode().equals(HttpStatus.OK)) {
                return Objects.requireNonNull(response.getBody())
                        .stream()
                        .filter(e -> e.toString().contains(filter))
                        .collect(Collectors.toList());
            }
        } catch (HttpClientErrorException e) {
            LOGGER.log(Level.WARNING, e.getResponseBodyAsString());
        }
        return Collections.emptyList();
    }
}