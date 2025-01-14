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
@RequestMapping("/api/user")
public class UserController {

    private UserService userService;

    private AdminService adminService;;

    //

    @GetMapping("/Check")
    public Boolean getAllUser() {
        List<User> users = userService.getAllUsers();
        return true;
    }

    // Changing this to where it check if the ID is valid

    @GetMapping("/Check/User/{id}")
    public boolean CheckUserByID(@PathVariable Long id) {
        return userService.CheckUserbyID(id);
    }

    @GetMapping("/Check/User")
    public boolean getUserById(@RequestBody UserDetails userDetails) {
        Optional<User> user =  userService.getUserById(userDetails);
        return user.isPresent();
    }

    @PostMapping("/addUser")
    public ResponseEntity<User> addUser(@RequestBody User User) {
        User user = userService.createUser(User);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteUser(@RequestBody UserDetails userDetails) {
        if (!adminService.CheckIfAdmin(userDetails.getEmail(), userDetails.getPassword())){
            return new ResponseEntity<>("You're not an admin", HttpStatus.NOT_FOUND);
        }
        if (userService.deleteUserById(userDetails)){
            return ResponseEntity.ok("User deleted successfully");
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/edit")
    public ResponseEntity<User> editUser(@RequestBody UserDetails userDetails, @RequestBody User user) {
        boolean isUpdated = userService.updateUser(userDetails, user);
        if (isUpdated) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
