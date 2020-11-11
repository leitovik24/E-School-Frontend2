package com.javamentor.service;

import com.javamentor.domain.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
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
public class TeacherService {

    private final RestTemplate restTemplate;

    @Value("${rest.host}/api/admin/teacher/teachers")
    private String URL;

    @Autowired
    public TeacherService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    Logger LOGGER = Logger.getLogger(TeacherService.class.getName());

    public void save(Teacher teacher) {
        try {
            ResponseEntity response =
                    restTemplate.postForEntity(URL, teacher, Teacher.class);
        } catch (HttpClientErrorException e) {
            LOGGER.log(Level.WARNING, e.getResponseBodyAsString());
        }
    }

    public List<Teacher> getAll(String search) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<?> httpEntity = new HttpEntity<>(headers);
        try {
            UriComponentsBuilder uriBuilder =
                    UriComponentsBuilder.fromHttpUrl(URL)
                            .queryParam("search", search);
            ResponseEntity<Teacher[]> response = restTemplate.exchange(
                    uriBuilder.toUriString(),
                    HttpMethod.GET,
                    httpEntity,
                    Teacher[].class
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