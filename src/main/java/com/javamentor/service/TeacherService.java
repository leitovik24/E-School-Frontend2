package com.javamentor.service;

import com.javamentor.domain.Teacher;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class TeacherService {

    public List<Teacher> getAll() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<Teacher>> response =
                restTemplate.exchange("http://localhost:8080/api/admin/teacher/teachers",
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<Teacher>>() {
                        });
        return response.getBody();
    }

}
