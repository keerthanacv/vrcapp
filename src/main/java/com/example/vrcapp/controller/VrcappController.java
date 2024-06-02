package com.example.vrcapp.controller;

import com.example.vrcapp.model.UserStatus;
import com.example.vrcapp.repository.UserStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VrcappController {

    @GetMapping("/test")
    public String getTestMessage() {
        return "Welcome! Its working";
    }

}
