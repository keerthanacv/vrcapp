package com.example.vrcapp.model;

import jakarta.persistence.*;

//import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "userstatus")
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
    private String roleType;

    @Column(name = "user_type")
    private String userType;

    @Column(name = "createdAt")
    private Date createdAt;

    @Column(name = "updatedAt")
    private Date updatedAt;

    @Column(name = "status")
    private String status;

}
