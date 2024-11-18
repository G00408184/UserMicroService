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
        user.setAdmin(false);
        return userRepository.save(user);

    }

    public boolean deleteUserById(long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public boolean updateUser(long userId, User updatedUser) {
        Optional<User> existingUserOptional = userRepository.findById(userId);

        if (existingUserOptional.isPresent()) {
            User existingUser = existingUserOptional.get();

            // Check if the isAdmin field is being changed
            if (existingUser.isAdmin() != updatedUser.isAdmin()) {
                throw new IllegalArgumentException("You cannot change the admin status.");
            }

            // Keep the same ID and save the updated user
            updatedUser.setId(userId);
            userRepository.save(updatedUser);
            return true;
        } else {
            return false; // User not found
        }
    }
}