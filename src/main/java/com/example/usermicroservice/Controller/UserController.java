package com.example.usermicroservice.Controller;
import com.example.usermicroservice.Entity.User;
import com.example.usermicroservice.Entity.UserDetails;
import com.example.usermicroservice.Service.AdminService;
import com.example.usermicroservice.Service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/api/admin")
public class UserController {

    private UserService userService;

    private AdminService adminService;;

    //

    @GetMapping("/Check")
    public Boolean getAllUser(@RequestBody UserDetails userDetails) {
        List<User> users = userService.getAllUsers();
        return true;
    }

    @GetMapping("/Check/{id}")
    public ResponseEntity<User> getUserById(@PathVariable long id) {
        Optional<User> user =  userService.getUserById(id);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/addUser")
    public ResponseEntity<User> addUser(@RequestBody User User) {
        User user = userService.createUser(User);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable long id, @RequestBody UserDetails userDetails) {
        if (!adminService.CheckIfAdmin(userDetails.getEmail(), userDetails.getPassword())){
            return new ResponseEntity<>("You're not an admin", HttpStatus.NOT_FOUND);
        }
        if (userService.deleteUserById(id)){
            return ResponseEntity.ok("User deleted successfully");
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<User> editUser(@PathVariable long id, @RequestBody User user) {
        boolean isUpdated = userService.updateUser(id, user);
        if (isUpdated) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
