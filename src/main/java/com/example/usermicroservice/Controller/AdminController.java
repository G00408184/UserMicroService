package com.example.usermicroservice.Controller;

import com.example.usermicroservice.Entity.UserDetails;
import com.example.usermicroservice.Service.AdminService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/admin")
@RestController
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/CheckAdmin")
    public boolean CheckIfAdmin(@RequestBody UserDetails userDetails) {
       return adminService.CheckIfAdmin(userDetails.getEmail(), userDetails.getPassword());
    }

    
}
