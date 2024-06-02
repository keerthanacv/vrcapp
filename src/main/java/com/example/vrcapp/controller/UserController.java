package com.example.vrcapp.controller;

import com.example.vrcapp.service.UserService;
import com.example.vrcapp.dto.UserRegistrationDto;
import com.example.vrcapp.model.UserStatus;
import com.example.vrcapp.util.ResponseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/auth")
public class UserController {

    @Autowired
    private UserService userService;

    //Admin Module
    @GetMapping("/admin/getUsersList")
    public List<UserStatus> getAllUnapprovedUsers() {
        return new ResponseEntity<>(userService.getAllUnapprovedUsers(), HttpStatus.OK).getBody();
    }

    @PostMapping("/register")
    public ResponseEntity<ResponseWrapper> register(@Valid @RequestBody UserRegistrationDto userDto) {
        UserStatus user = userService.createUser(userDto);
        return new ResponseEntity<>(new ResponseWrapper(201, "Success", user), HttpStatus.CREATED);
    }



}
