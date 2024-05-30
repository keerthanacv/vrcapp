package com.example.vrcapp.repository;

import com.example.vrcapp.model.UserEntity;
import com.example.vrcapp.model.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RegistrationRepository  extends JpaRepository<UserStatus,Integer> {

}
