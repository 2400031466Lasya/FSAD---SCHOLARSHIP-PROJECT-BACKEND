package com.klu.scholarship.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.klu.scholarship.dto.LoginRequestDTO;
import com.klu.scholarship.entity.User;
import com.klu.scholarship.exception.ResourceNotFoundException;
import com.klu.scholarship.repository.UserRepository;
import com.klu.scholarship.security.JwtUtil;

@Service
public class AuthService {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public String login(LoginRequestDTO dto) {

	    User user = userRepo.findByEmail(dto.getEmail());

	    if (user == null) {
	        throw new RuntimeException("User not found");
	    }

	    if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
	        throw new RuntimeException("Invalid password");
	    }

	    // 🔥 generate token with role
	    String token = jwtUtil.generateToken(user);

	    return token; // ✅ VERY IMPORTANT
	}
}