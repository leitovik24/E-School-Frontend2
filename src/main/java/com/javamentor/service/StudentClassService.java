package com.javamentor.service;

import com.javamentor.domain.Student;
import com.javamentor.domain.StudentClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class StudentClassService {

    @Value("${rest.host}/api/admin/studentClass")
    private String url;
    private final RestTemplate restTemplate;

    @Autowired
    public StudentClassService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    Logger LOGGER = Logger.getLogger(StudentService.class.getName());

    /**
     * Метод получения списка классов
     * @return - Список классов
     */
    public List<StudentClass> getAllStudentClass(){
        try {
            ResponseEntity<StudentClass[]> response = restTemplate.getForEntity(url, StudentClass[].class);
            List<StudentClass> result = Arrays.asList(Objects.requireNonNull(response.getBody()));
            if (response.getStatusCode().equals(HttpStatus.OK)) {
                return result;
            }
        }catch (HttpClientErrorException e){
            LOGGER.log(Level.WARNING, e.getResponseBodyAsString());
        }
        return Collections.emptyList();
    }

    /**
     * Метод вывода списка студентов в классе
     * @param id - id класса, для получения списка студентов
     * @return - Список студентов
     */
    public List<Student> getAllStudentsOfStudentClassById(Long id){
        try {
            ResponseEntity<Student[]> response = restTemplate.getForEntity(url + "/" + id + "/students", Student[].class);
            List<Student> result = Arrays.asList(Objects.requireNonNull(response.getBody()));
            if (response.getStatusCode().equals(HttpStatus.OK)) {
                return result;
            }
        }catch (HttpClientErrorException e){
            LOGGER.log(Level.WARNING, e.getResponseBodyAsString());
        }
        return Collections.emptyList();
    }
}
