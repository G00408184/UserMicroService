package com.example.usermicroservice.Controller;

import com.example.usermicroservice.Entity.User;
import com.example.usermicroservice.Service.UserService;
import com.example.usermicroservice.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Book;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/api/admin")
public class UserController {

    private UserService userService;

    @GetMapping("/get")
    public ResponseEntity<List<User>> getAllBooks() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<User> getBookById(@PathVariable long id) {
        Optional<User> user =  userService.getUserById(id);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public ResponseEntity<User> addBook(@RequestBody User User) {
        User user = userService.createUser(User);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<User> deleteBook(@PathVariable long id) {
        if (userService.deleteUserById(id)){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<User> editBook(@PathVariable long id, @RequestBody User User) {
        boolean isUpdated = userService.updateUserById(id, User);
        if (isUpdated) {
            return ResponseEntity.ok(User);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
