package com.example.vrcapp.dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;

@Getter
@Setter
@Data
public class UserRegistrationDto {
    @Email
    @NotBlank(message = "Email Id Cannot be blank")
    @Size(max = 100)
    @NotNull(message = "Email Id cannot be null")
    private String emailId;

    @NotBlank(message = "Password cannot be blank")
    @javax.validation.constraints.Size(min = 8, max = 200)
    private String password;

    private String confirmpassword;

    @NotBlank
    private String type;

    @NotBlank
    private String name;

}
