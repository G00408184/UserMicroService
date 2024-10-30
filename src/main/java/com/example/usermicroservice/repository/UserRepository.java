package com.example.usermicroservice.repository;

import com.example.usermicroservice.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
