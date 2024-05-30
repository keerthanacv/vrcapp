package com.example.vrcapp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name="password")
    private String password;

    @Column(name="emailId")
    private String emailId;

    @Column(name="role_type")
    private String roleType;

    @Column(name="user_type")
    private String userType;

    @Column(name = "createdAt")
    private LocalDateTime createdAt;

    @Column(name="updatedAt")
    private LocalDateTime updatedAt;
}
