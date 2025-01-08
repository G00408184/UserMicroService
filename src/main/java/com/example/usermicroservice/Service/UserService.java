package com.example.usermicroservice.Service;

import com.example.usermicroservice.Entity.User;
import com.example.usermicroservice.Entity.UserDetails;
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

    public Optional<User> getUserById(UserDetails userDetails) {

        return userRepository.findByEmailAndPassword(userDetails.getEmail(), userDetails.getPassword());
    }

    public boolean CheckUserbyID(long id) {

        return userRepository.existsById(id);

    }

    public User createUser(User user) {
        user.setAdmin(false);
        return userRepository.save(user);

    }

    public boolean deleteUserById(UserDetails userDetails) {
        Optional<User> user = userRepository.findByEmailAndPassword(userDetails.getEmail(), userDetails.getPassword());
        if (user.isPresent()) {
            userRepository.deleteById(user.get().getId());
            return true;
        }
        return false;
    }

    public boolean updateUser(UserDetails userDetails, User updatedUser) {
        Optional<User> existingUserOptional = userRepository.findByEmailAndPassword(userDetails.getEmail(), userDetails.getPassword());

        if (existingUserOptional.isPresent()) {
            User existingUser = existingUserOptional.get();

            // Check if the isAdmin field is being changed
            if (existingUser.isAdmin() != updatedUser.isAdmin()) {
                throw new IllegalArgumentException("You cannot change the admin status.");
            }

            // Keep the same ID and save the updated user
            updatedUser.setId(existingUserOptional.get().getId());
            userRepository.save(updatedUser);
            return true;
        } else {
            return false; // User not found
        }
    }
}