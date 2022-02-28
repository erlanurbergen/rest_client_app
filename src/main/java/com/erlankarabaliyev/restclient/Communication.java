package com.erlankarabaliyev.restclient;


import com.erlankarabaliyev.restclient.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class Communication {


    @Autowired
    private RestTemplate restTemplate;

    private final String constURL = "http://localhost:8080/spring_mvc/api/employees";


    public List<Employee> getAllEmployees() {
        ResponseEntity<List<Employee>> responseEntity =
                restTemplate.exchange(constURL, HttpMethod.GET, null, new ParameterizedTypeReference<List<Employee>>() {
                });
        return responseEntity.getBody();
    }

    public Employee getEmployee(int id){

        Employee employee = restTemplate.getForObject(constURL + "/" + id, Employee.class);
        return employee;
    }

    public void saveEmployee(Employee employee) {
        int id = employee.getId();
        if (id == 0) {
            ResponseEntity<String> responseEntity = restTemplate.postForEntity(constURL, employee, String.class);
            System.out.println("added");
            System.out.println(responseEntity.getBody());
        } else {
            restTemplate.put(constURL, employee);
            System.out.println("Employee with id " + id + " was updated");
        }
    }

    public void deleteEmployee(int id) {
        restTemplate.delete(constURL + "/" + id);
    }
}
