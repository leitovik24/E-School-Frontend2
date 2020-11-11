package com.javamentor.service;

import com.javamentor.domain.Student;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class StudentService {

    private final RestTemplate restTemplate;

    @Value("${rest.host}/api/admin/student/students")
    private String URL;

    public StudentService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    Logger LOGGER = Logger.getLogger(StudentService.class.getName());

    public void save(Student student) {
        try {
            ResponseEntity<?> response =
                    restTemplate.postForEntity(URL, student, Student.class);
        } catch (HttpClientErrorException e) {
            LOGGER.log(Level.WARNING, e.getResponseBodyAsString());
        }
    }

    public List<Student> getAll(String search) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<?> httpEntity = new HttpEntity<>(headers);
        try {
            UriComponentsBuilder uriBuilder =
                    UriComponentsBuilder.fromHttpUrl(URL)
                            .queryParam("search", search);
            ResponseEntity<Student[]> response = restTemplate.exchange(
                    uriBuilder.toUriString(),
                    HttpMethod.GET,
                    httpEntity,
                    Student[].class
            );
            if (response.getStatusCode().equals(HttpStatus.OK)) {
                return Arrays.asList(Objects.requireNonNull(response.getBody()));
            }
        } catch (HttpClientErrorException e) {
            LOGGER.log(Level.WARNING, e.getResponseBodyAsString());
        }
        return Collections.emptyList();
    }
}