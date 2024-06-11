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
    @NotNull(message = "Password Id cannot be null")
    @NotBlank(message = "Password cannot be blank")
    @javax.validation.constraints.Size(min = 8, max = 200)
    private String password;
    @NotNull(message = "Confirm password cannot be null")
    @NotBlank(message = "Confirm password cannot be blank")
    private String confirmpassword;
    @NotNull(message = "Type cannot be null")
    @NotBlank(message = "Type cannot be blank")
    private String type;
    @NotNull(message = "Name cannot be null")
    @NotBlank(message = "Name cannot be blank")
    private String name;

}
