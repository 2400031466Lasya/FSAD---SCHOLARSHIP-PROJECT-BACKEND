package com.klu.scholarship.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.klu.scholarship.entity.User;
import com.klu.scholarship.repository.UserRepository;
import com.klu.scholarship.service.AuthService;
import com.klu.scholarship.dto.LoginRequestDTO;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")   // ✅ CORRECT BASE PATH
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // ✅ REGISTER
    @PostMapping("/register")
    public Map<String, Object> register(@RequestBody User user) {

        // ✅ check duplicate email
        if (userRepository.findByEmail(user.getEmail()) != null) {
            throw new RuntimeException("Email already exists");
        }

        // ✅ default role
        if (user.getRole() == null || user.getRole().isEmpty()) {
            user.setRole("STUDENT");
        }

        // ✅ encrypt password
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        User savedUser = userRepository.save(user);
        Map<String, Object> response = new HashMap<>();

        response.put("message", "User registered successfully");
        response.put("email", savedUser.getEmail());
        response.put("role", savedUser.getRole());

        return response;
    }

    // ✅ LOGIN
    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody LoginRequestDTO dto) {

        String token = authService.login(dto);

        User user = userRepository.findByEmail(dto.getEmail());

        Map<String, Object> response = new HashMap<>();

        response.put("token", token);
        response.put("email", user.getEmail());
        response.put("role", user.getRole());
        response.put("userId", user.getId()); // 🔥 ADD THIS LINE

        return response;
    }
}