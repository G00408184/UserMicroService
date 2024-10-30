package com.example.usermicroservice.Controller;

import com.example.usermicroservice.Service.AdminService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/admin")
@RestController
public class AdminController {

    private final AdminService emailService;

    public AdminController(AdminService emailService) {
        this.emailService = emailService;
    }

    @GetMapping("/test-email")
    public boolean CheckIfAdmin(Long ID) {
       return emailService.CheckIfAdmin(ID);
    }
}
