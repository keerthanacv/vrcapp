package com.example.vrcapp.model;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "users")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Component
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
    private LocalDate createdAt;

    @Column(name="updatedAt")
    private LocalDate updatedAt;

    public UserEntity buildUserEntity(Registration registration)
    {
        return UserEntity.builder()
                .name(registration.getName())
                .password(registration.getPassword())
                .emailId(registration.getEmailId())
                .roleType(registration.getType())
                .userType(registration.getType())
                .createdAt(LocalDate.now())
                .updatedAt(LocalDate.now())
                .id(100).build();
    }

}
