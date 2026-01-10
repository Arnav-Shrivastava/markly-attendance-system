package com.markly.backend.controller;

import com.markly.backend.model.Role;
import com.markly.backend.model.User;
import com.markly.backend.repository.UserRepository;
import com.markly.backend.security.JwtService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthController(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    @PostMapping("/register")
    public String registerUser(@RequestBody User user) {

        user.setRole(Role.STUDENT);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);

        return "User registered successfully";
    }

    @PostMapping("/login")
    public Map<String, String> loginUser(@RequestBody User loginRequest) {

        User user = userRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        boolean passwordMatches =
                passwordEncoder.matches(loginRequest.getPassword(), user.getPassword());

        if (!passwordMatches) {
            throw new RuntimeException("Wrong password");
        }

        // 🔐 CREATE JWT
        String token = jwtService.generateToken(
                user.getEmail(),
                user.getRole().name());

        return Map.of(
                "token", token,
                "email", user.getEmail()
        );
    }



}
