package com.javamentor.service;

import com.javamentor.domain.Teacher;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@Service
public class TeacherService {

    public List<Teacher> getAll() {
        String URL = "http://localhost:8080/api/admin/teacher/teachers";
        try {
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<List<Teacher>> response =
                    restTemplate.exchange(URL,
                            HttpMethod.GET, null, new ParameterizedTypeReference<List<Teacher>>() {
                            });
            if (response.getStatusCodeValue() == 200 && response.getBody() != null) {
                return response.getBody();
            }
        } catch (RestClientResponseException e) {
            //           return ResponseEntity.status(e.getRawStatusCode()).body(e.getResponseBodyAsString());
        }
        return Collections.emptyList();
    }
}
