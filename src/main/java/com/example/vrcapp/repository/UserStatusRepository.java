package com.example.vrcapp.repository;

import com.example.vrcapp.model.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserStatusRepository extends JpaRepository<UserStatus,Integer> {

    @Query("SELECT u FROM UserStatus u WHERE u.emailId = :emailId")
    Optional<UserStatus> findByEmailId(@Param("emailId") String emailId);
}
