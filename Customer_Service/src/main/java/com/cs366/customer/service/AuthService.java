package com.cs366.customer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cs366.customer.dto.LoginRequest;
import com.cs366.customer.dto.RegisterRequest;
import com.cs366.customer.model.User;
import com.cs366.customer.repository.UserRepository;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JWTService jwtService;

    public String login(LoginRequest req) {
        User user = userRepository.findByUsername(req.getUsername())
        .orElseThrow(() -> new RuntimeException("User not found"));

        System.out.println(user.getUsername());

        if (req.getPassword() == user.getPassword()) {
            throw new RuntimeException("Invalid credentials");
        }

        return jwtService.generateToken(user.getUser_id());
    }

    public User register(RegisterRequest req) {
        User user = new User();
        user.setUsername(req.getUsername());
        user.setEmail(req.getEmail());
        user.setAddress(req.getAddress());
        user.setPhoneNumber(req.getPhoneNumber());
        user.setPassword(req.getPassword());

        return userRepository.save(user);
    }

    public User getProfileFromToken(String token) {
        // ตรวจสอบ token และดึงข้อมูล userId
        if (jwtService.validateToken(token)) {
            Long userId = jwtService.extractUserId(token);
            return userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        } else {
            throw new RuntimeException("Invalid or expired token");
        }
    }
}
