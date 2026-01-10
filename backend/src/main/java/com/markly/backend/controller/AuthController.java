package com.markly.backend.controller;

import com.markly.backend.model.Role;
import com.markly.backend.model.User;
import com.markly.backend.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public String registerUser(@RequestBody User user) {

        user.setRole(Role.STUDENT);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);

        return "User registered successfully";
    }

    @PostMapping("/login")
    public String loginUser(@RequestBody User loginRequest) {

        //1.find user by email
        User user = userRepository.findByEmail(loginRequest.getEmail()).orElseThrow(() -> new RuntimeException("User not found"));

        //2.Check password
        boolean passwordMatches =  passwordEncoder.matches(loginRequest.getPassword(), user.getPassword());

        if(!passwordMatches) {
            throw new RuntimeException("Wrong password");
        }

        //3.Login Succesful

        return "Login successful";
    }
}
