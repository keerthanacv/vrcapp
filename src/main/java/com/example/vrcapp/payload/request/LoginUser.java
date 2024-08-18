package com.example.vrcapp.payload.request;

import com.example.vrcapp.model.Users;
import com.example.vrcapp.repository.UsersRepository;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;
import java.util.Optional;


@Getter
@Setter
public class LoginUser  {

    @NotBlank(message = "Email Id cannot be blank")
    private String emailId;

    @NotBlank(message = "Password cannot be blank")
    private String password;

}
