package com.javamentor.service;

import com.javamentor.domain.Student;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class StudentService {

    private final RestTemplate restTemplate;

    @Value("${rest.host}/api/admin/student/students/")
    private String URL;

    public StudentService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    Logger LOGGER = Logger.getLogger(StudentService.class.getName());

    public void save(Student student) {
        try {
            ResponseEntity response =
                    restTemplate.postForEntity(URL, student, Student.class);
        } catch (HttpClientErrorException e) {
            LOGGER.log(Level.WARNING, e.getResponseBodyAsString());
        }
    }

    public List<Student> searchList(String search) {
        try {
            ResponseEntity<List<Student>> response =
                    restTemplate.exchange(URL,
                            HttpMethod.GET, null, new ParameterizedTypeReference<List<Student>>() {
                            }, search);
            if (response.getStatusCode().equals(HttpStatus.OK)) {
                return response.getBody();
            }
        } catch (HttpClientErrorException e) {
            LOGGER.log(Level.WARNING, e.getResponseBodyAsString());
        }
        return Collections.emptyList();
    }

    public List<Student> getAll(String search) {
        try {
            UriComponentsBuilder uriBuilder =
                    UriComponentsBuilder.fromHttpUrl(URL)
                            .queryParam("search", search);
            ResponseEntity<List<Student>> response = restTemplate.exchange(
                    uriBuilder.toUriString(),
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<Student>>() {
                    }
            );
            if (response.getStatusCode().equals(HttpStatus.OK)) {
                return response.getBody();
            }
        } catch (HttpClientErrorException e) {
            LOGGER.log(Level.WARNING, e.getResponseBodyAsString());
        }
        return Collections.emptyList();
    }
}