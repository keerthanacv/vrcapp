package com.example.vrcapp.service;

import com.example.vrcapp.dto.UserRegistrationDto;
import com.example.vrcapp.exception.ApiException;
import com.example.vrcapp.model.UserStatus;
import com.example.vrcapp.repository.UserStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserStatusRepository userStatusRepository;

    @Autowired
    private PasswordEncoder  passwordEncoder;

    public UserStatus getUserById(int id) {
        return userStatusRepository.findById(id).orElse(null);
    }

    public List<UserStatus> getAllUnapprovedUsers() {
        return userStatusRepository.findAll();
    }

    public UserStatus createUser(UserRegistrationDto userDto) {
        Optional<UserStatus> userOpt = userStatusRepository.findByEmailId(userDto.getEmailId());
        System.out.println("userOpt:"+userOpt);
        System.out.println("pass1:"+userDto.getPassword());
        System.out.println("pass2:"+userDto.getConfirmpassword());
        if (userOpt.isPresent()) {
            throw new ApiException(400, "EMAIL ID ALREADY PRESENT");
        }

        if (!userDto.getPassword().equals(userDto.getConfirmpassword())) {
            throw new ApiException(400, "PASSWORDS DOES NOT MATCH");
        }

        UserStatus userDetails = new UserStatus();
        userDetails.setEmailId(userDto.getEmailId());
        userDetails.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userDetails.setName(userDto.getName());
        userDetails.setUserType(userDto.getType());
        userDetails.setRoleType(userDto.getType());
        userDetails.setStatus("NV");

        try {
            return userStatusRepository.save(userDetails);
        } catch (Exception e) {
            throw new ApiException(500, "Error while creating user");
        }
    }
}
