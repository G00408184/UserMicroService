package com.example.usermicroservice.repository;

import com.example.usermicroservice.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.Email = :email AND u.Password = :password")
    Optional<User> findByEmailAndPassword( String email,String password);

}
