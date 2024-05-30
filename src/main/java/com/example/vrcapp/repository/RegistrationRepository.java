package com.example.vrcapp.repository;

import com.example.vrcapp.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RegistrationRepository  extends JpaRepository<UserEntity,String> {

    UserEntity getById(int id);
}
