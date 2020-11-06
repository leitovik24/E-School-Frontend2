package com.javamentor.service;

import com.javamentor.domain.Teacher;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class TeacherService {
    String URL = "http://localhost:8080/api/admin/teacher/teachers";
    Logger LOGGER = Logger.getLogger(TeacherService.class.getName());

    public List<Teacher> getAll() {
        try {
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<List<Teacher>> response =
                    restTemplate.exchange(URL,
                            HttpMethod.GET, null, new ParameterizedTypeReference<List<Teacher>>() {
                            });
            if (response.getStatusCode().equals(HttpStatus.OK)) {
                return response.getBody();
            }
        } catch (RestClientResponseException e) {
            LOGGER.log(Level.WARNING, "Error status code: {0} , Message: {1}",
                    new String[]{String.valueOf(e.getRawStatusCode()), e.getMessage()});
        }
        return Collections.emptyList();
    }
}