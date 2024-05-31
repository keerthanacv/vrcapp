package com.example.vrcapp.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

//import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "userstatus")
@Getter
@Setter
public class UserStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

   @Column(name = "password")
    private String password;

    @Column(name = "emailId")
    private String emailId;

    @Column(name = "role_type")
    private String role_type;

    @Column(name = "user_type")
    private String user_type;

    @Column(name = "createdAt")
    private Date createdAt;

   // @Column(name = "updatedAt")
    private Date updatedAt;

   // @Column(name = "status")
    private String status;

}
