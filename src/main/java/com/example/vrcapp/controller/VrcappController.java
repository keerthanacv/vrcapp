package com.example.vrcapp.controller;

import com.example.vrcapp.model.Employee;
import com.example.vrcapp.model.Registration;
import com.example.vrcapp.model.UserEntity;
import com.example.vrcapp.model.UserStatus;
import com.example.vrcapp.repository.RegistrationRepository;
import com.example.vrcapp.repository.UserRepository;
import com.example.vrcapp.service.EmployeeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class VrcappController {

    @Autowired
    RegistrationRepository registrationRepository;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    UserRepository userRepository;

    @Autowired
            UserEntity userEntity;
    ObjectMapper objectMapper = new ObjectMapper();

    @GetMapping("/userstatus/{id}")
    public UserStatus getUserStatus(@PathVariable int id) throws JsonProcessingException {

        UserStatus userStatus =  registrationRepository.findById(id).orElse(null);
        System.out.println(objectMapper.writeValueAsString(registrationRepository.findById(id).orElse(null)));
        System.out.println("User status "+objectMapper.writeValueAsString(userStatus));
        return userStatus;

    }

    @GetMapping("/user/{id}")
    public UserEntity getUser(@PathVariable int id) throws JsonProcessingException {

        UserEntity userEntity =  userRepository.findById(id).orElse(null);
        System.out.println(objectMapper.writeValueAsString(registrationRepository.findById(id).orElse(null)));
        System.out.println("User status "+objectMapper.writeValueAsString(userEntity));
        return userEntity;

    }

    @GetMapping("/employees")
    public List<Employee> getAllEmployees() throws JsonProcessingException {
        System.out.println(objectMapper.writeValueAsString(employeeService.getAllEmployees()));
        return employeeService.getAllEmployees();
    }

    @PostMapping("/auth/register")
    public void registerUSer(Registration registration)
    {
        userRepository.save(userEntity.buildUserEntity(registration));
    }


}
