package com.example.usermicroservice.Service;

import com.example.usermicroservice.Entity.User;
import com.example.usermicroservice.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor

public class UserService {

    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();

    }

    public Optional<User> getUserById(long id) {
        return userRepository.findById(id);

    }


    public User createUser(User user) {
        return userRepository.save(user);

    }

    public boolean deleteUserById(long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public boolean updateUserById(long id, User user) {
        if (userRepository.existsById(id)) {
            user.setId(id);
            userRepository.save(user);
            return true;
        } else {
            return false;
        }
    }
}
