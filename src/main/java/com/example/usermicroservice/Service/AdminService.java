package com.example.usermicroservice.Service;

import com.example.usermicroservice.Entity.User;
import com.example.usermicroservice.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AdminService {
    private UserRepository USERRepository;

    public boolean CheckIfAdmin(String email, String password) {

        Optional<User> user1 = USERRepository.findByEmailAndPassword(email, password);
        if (user1.isPresent()) {
            return user1.get().isAdmin();
         } else {
            return false;
        }
    }
}
