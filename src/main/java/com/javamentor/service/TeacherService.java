package com.javamentor.service;

import com.javamentor.domain.Teacher;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class TeacherService {

    public List<Teacher> getAll() {
        try {
            RestTemplate restTemplate = new RestTemplate();
            String URL = "http://localhost:8080/api/admin/teacher/teachers";
            ResponseEntity<List<Teacher>> response =
                    restTemplate.exchange(URL,
                            HttpMethod.GET, null, new ParameterizedTypeReference<List<Teacher>>() {
                            });
            if (response.getStatusCodeValue() == 200 && response.getBody() != null) {
                return response.getBody();
            }
        } catch (HttpStatusCodeException ignored) {
        }
        return null;
    }
}
