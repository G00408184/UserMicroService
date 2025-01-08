package com.example.usermicroservice.Service;

import com.example.usermicroservice.Entity.User;
import com.example.usermicroservice.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllUsers() {
        User user1 = new User(1L, "John Doe", "1990-01-01", "password123", "johndoe@example.com", "123 Main St", 1234567890, false);
        User user2 = new User(2L, "Jane Doe", "1992-05-15", "password456", "janedoe@example.com", "456 Elm St", 987654321, false);

        when(userRepository.findAll()).thenReturn(Arrays.asList(user1, user2));

        List<User> users = userService.getAllUsers();

        assertEquals(2, users.size());
        assertEquals("John Doe", users.get(0).getName());
        assertEquals("Jane Doe", users.get(1).getName());
        verify(userRepository, times(1)).findAll();
    }

    @Test
    void getUserById() {
        User user = new User(1L, "John Doe", "1990-01-01", "password123", "johndoe@example.com", "123 Main St", 1234567890, false);

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        Optional<User> result = userService.getUserById(1L);

        System.out.println("User fetched: " + result.get());
        assertTrue(result.isPresent());
    }


    @Test
    void createUser() {
        User user = new User(null, "John Doe", "1990-01-01", "password123", "johndoe@example.com", "123 Main St", 1234567890, false);

        when(userRepository.save(user)).thenReturn(user);

        User createdUser = userService.createUser(user);

        assertNotNull(createdUser);
        assertFalse(createdUser.isAdmin());
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void deleteUserById() {
        when(userRepository.existsById(1L)).thenReturn(true);

        boolean result = userService.deleteUserById(1L);

        assertTrue(result);
        verify(userRepository, times(1)).existsById(1L);
        verify(userRepository, times(1)).deleteById(1L);
    }

    @Test
    void updateUser() {
        User existingUser = new User(1L, "John Doe", "1990-01-01", "password123", "johndoe@example.com", "123 Main St", 1234567890, false);
        User updatedUser = new User(1L, "John Smith", "1990-01-01", "password456", "johnsmith@example.com", "456 Elm St", 987654321, false);

        when(userRepository.findById(1L)).thenReturn(Optional.of(existingUser));
        when(userRepository.save(updatedUser)).thenReturn(updatedUser);

        boolean result = userService.updateUser(1L, updatedUser);

        assertTrue(result);
        verify(userRepository, times(1)).findById(1L);
        verify(userRepository, times(1)).save(updatedUser);
    }

    @Test
    void updateUserAdminStatusThrowsException() {
        User existingUser = new User(1L, "John Doe", "1990-01-01", "password123", "johndoe@example.com", "123 Main St", 1234567890, false);
        User updatedUser = new User(1L, "John Smith", "1990-01-01", "password456", "johnsmith@example.com", "456 Elm St", 987654321, true);

        when(userRepository.findById(1L)).thenReturn(Optional.of(existingUser));

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                userService.updateUser(1L, updatedUser)
        );
        assertEquals("You cannot change the admin status.", exception.getMessage());
    }
}
