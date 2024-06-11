package com.example.vrcapp.payload.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;


@Getter
@Setter
public class LoginUser {

    @NotBlank(message = "Email Id cannot be blank")
    private String emailId;

    @NotBlank(message = "Password cannot be blank")
    private String password;
}
