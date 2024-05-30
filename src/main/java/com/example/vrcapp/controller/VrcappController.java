package com.example.vrcapp.controller;

import com.example.vrcapp.model.UserEntity;
import com.example.vrcapp.model.UserStatus;
import com.example.vrcapp.model.UserStatus;
import com.example.vrcapp.repository.RegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class VrcappController {

    @Autowired
    RegistrationRepository registrationRepository;

    @GetMapping("/user/{id}")
    public UserStatus getUser(@PathVariable int id) {
        return registrationRepository.findById(id).orElse(null);
    }

}
