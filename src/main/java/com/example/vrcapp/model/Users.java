package com.example.vrcapp.model;

import com.example.vrcapp.repository.UsersRepository;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Entity
@Getter
@Setter
@Table(name = "users")
public class Users implements UserDetails {
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

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }


    @Autowired
    private UsersRepository usersRepository;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Optional<Users> user = usersRepository.findByEmailId(emailId);
        return List.of(new SimpleGrantedAuthority(user.get().getRoleType()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return emailId;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
