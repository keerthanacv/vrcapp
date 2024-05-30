package com.example.vrcapp.controller;

import com.example.vrcapp.repository.RegistrationRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class VrcappController {

    @Autowired
    RegistrationRepository registrationRepository;

    ObjectMapper objectMapper = new ObjectMapper();
    @GetMapping("/user")
    public void getUser()
    {
       System.out.println(registrationRepository.getById(1));

    }
}
