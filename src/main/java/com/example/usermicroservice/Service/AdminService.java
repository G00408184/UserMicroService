package com.example.usermicroservice.Service;

import com.example.usermicroservice.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AdminService {
    private UserRepository USERRepository;
    public boolean CheckIfAdmin(Long id) {

    return USERRepository.findById(id).get().isAdmin();

    }
}
