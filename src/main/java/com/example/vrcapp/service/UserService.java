package com.example.vrcapp.service;

import com.example.vrcapp.dto.UserRegistrationDto;
import com.example.vrcapp.exception.ApiException;
import com.example.vrcapp.model.UserStatus;
import com.example.vrcapp.model.Users;
import com.example.vrcapp.payload.request.LoginUser;
import com.example.vrcapp.repository.UserStatusRepository;
import com.example.vrcapp.repository.UsersRepository;
import com.example.vrcapp.util.ResponseWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.file.LinkOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    private static Logger LOGGER = LoggerFactory.getLogger(UserService.class);
    @Autowired
    private UserStatusRepository userStatusRepository;

    @Autowired
    private PasswordEncoder  passwordEncoder;

    @Autowired
    private UsersRepository usersRepository;

    public UserStatus getUserById(int id) {
        return userStatusRepository.findById(id).orElse(null);
    }

    public ResponseWrapper getAllUnapprovedUsers() {
        LOGGER.info("In the service class to get all unapproved users");
        return new ResponseWrapper(200,"",userStatusRepository.findAll(),new ArrayList<>());
    }

    public UserStatus createUser(UserRegistrationDto userDto) throws Exception{
        Optional<UserStatus> userOpt = userStatusRepository.findByEmailId(userDto.getEmailId());
        System.out.println("userOpt:"+userOpt);
        System.out.println("pass1:"+userDto.getPassword());
        System.out.println("pass2:"+userDto.getConfirmpassword());
        if (userOpt.isPresent()) {
            throw new ApiException(1L,"EMAIL ID ALREADY PRESENT", HttpStatusCode.valueOf(400));
        }

        if (!userDto.getPassword().equals(userDto.getConfirmpassword())) {
            throw new ApiException(1L,"PASSWORDS DOESN'T MATCH", HttpStatusCode.valueOf(400));
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
            throw new ApiException(1L,"SOMETHING WENT WRONG", HttpStatusCode.valueOf(500));
        }
    }

    public Users loginRegisteredUser(LoginUser loginUser) throws Exception {
       Optional<Users> user = usersRepository.findByEmailId(loginUser.getEmailId());
       if(user.isPresent())
           return user.get();
       else
           throw new Exception();
    }
    public Users getUser(String email) throws Exception {
        Optional<Users> user = usersRepository.findByEmailId(email);
        if(user.isPresent())
            return user.get();
        else
            throw new Exception();
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Users> user = usersRepository.findByEmailId(email);
        if(user.isPresent())
            return user.get();
        else
            throw new UsernameNotFoundException("No username found");
    }
}
