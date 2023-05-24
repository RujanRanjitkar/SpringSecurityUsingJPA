package com.example.springsecurity.controller;

import com.example.springsecurity.model.User;
import com.example.springsecurity.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private final UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;
    public UserController(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @GetMapping("/")
    public String home(){
        return "Hello World";
    }

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping("/user")
    public String user(){
        return "Hello User";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin")
    public String admin(){
        return "Hello Admin";
    }

    @PostMapping("/addNewUser")
    public User addNewUser(@RequestBody User user){
        String pwd=user.getPassword();
        String encodedPwd=passwordEncoder.encode(pwd);
        user.setPassword(encodedPwd);
        userRepo.save(user);
        return user;
    }
}
