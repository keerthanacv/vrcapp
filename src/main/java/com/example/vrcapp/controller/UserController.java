package com.example.vrcapp.controller;

import com.example.vrcapp.payload.request.LoginUser;
import com.example.vrcapp.service.UserService;
import com.example.vrcapp.dto.UserRegistrationDto;
import com.example.vrcapp.model.UserStatus;
import com.example.vrcapp.util.ResponseWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/auth")
public class UserController {

    private static Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;

    //Admin Module
    @GetMapping("/admin/getUsersList")
    public ResponseEntity<?> getAllUnapprovedUsers() {
        LOGGER.info("Getting all users list");
            return new ResponseEntity<>(userService.getAllUnapprovedUsers(), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<ResponseWrapper> register(@Valid @RequestBody UserRegistrationDto userDto) throws Exception{
        UserStatus user = userService.createUser(userDto);
        return new ResponseEntity<>(new ResponseWrapper(201, "Success",Arrays.asList(user),new ArrayList<>()), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity loginUser(@Valid @RequestBody LoginUser loginUser) throws Exception {
        userService.loginRegisteredUser(loginUser);
        return new ResponseEntity("success",HttpStatus.OK);
    }



}
