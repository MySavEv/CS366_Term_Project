package com.cs366.restaurant.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cs366.restaurant.dto.LoginRequest;
import com.cs366.restaurant.dto.RegisterRequest;
import com.cs366.restaurant.model.User;
import com.cs366.restaurant.service.AuthService;
import com.cs366.restaurant.utils.ResponseHandler;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest req) {
        try {
            String token = authService.login(req);
            return ResponseHandler.generateResponse("Login successful", true, Map.of("token", token));
        } catch (Exception e) {
            return ResponseHandler.generateResponse("Login failed", false, e.getMessage());
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest req) {
        try {
            User user = authService.register(req);
            return ResponseHandler.generateResponse("User registered successfully", true, user);
        } catch (Exception e) {
            return ResponseHandler.generateResponse("Registration failed", false, e.getMessage());
        }
    }

    @GetMapping("/me")
    public ResponseEntity<Object> getProfile(@RequestHeader("Authorization") String token) {
        try {
            User user = authService.getProfileFromToken(token);
            return ResponseHandler.generateResponse("User profile", true, user);
        } catch (Exception e) {
            return ResponseHandler.generateResponse("Failed to fetch user profile", false, e.getMessage());
        }
    }
}