package com.example.vrcapp.model;


import lombok.*;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Registration {

    private String emailId;

    private String password;

    private String confirmpassword;

    private String type;

    private String name;

}
