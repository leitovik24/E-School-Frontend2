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
    String URL = "http://localhost:8080/api/admin/teacher/teachers";

    public List<Teacher> getAll(String filter) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<List<Teacher>> response =
                    restTemplate.exchange(URL,
                            HttpMethod.GET, null, new ParameterizedTypeReference<List<Teacher>>() {
                            });
            if (response.getStatusCode().equals(HttpStatus.OK)) {
                return Objects
                        .requireNonNull(response.getBody())
                        .stream()
                        .filter(e -> e.getFirstName()
                                .contains(filter))
                        .collect(Collectors.toList());
            }
        }
        catch (Exception ignored) {
            // Exception
        }
        return Collections.emptyList();
    }
}
