package com.cs366.customer.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cs366.customer.dto.LoginRequest;
import com.cs366.customer.dto.RegisterRequest;
import com.cs366.customer.dto.UserEventPayload;
import com.cs366.customer.model.User;
import com.cs366.customer.service.AuthService;
import com.cs366.customer.service.KafkaProducerService;
import com.cs366.customer.service.UserService;
import com.cs366.customer.utils.ResponseHandler;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private KafkaProducerService kafkaService;

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest req) {
        try {
            String token = authService.login(req);
            UserEventPayload payload = new UserEventPayload();
            User user = userService.findById(authService.getProfileFromToken(token).getUser_id());
            payload.setUserId(String.valueOf(user.getUser_id()));
            payload.setEmail(user.getEmail());
            payload.setFullName(user.getUsername());
            kafkaService.sendUserLoggedInEvent(payload);
            return ResponseHandler.generateResponse("Login successful", true, Map.of("token", token));
        } catch (Exception e) {
            return ResponseHandler.generateResponse("Login failed", false, e.getMessage());
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest req) {
        try {
            User user = authService.register(req);
            UserEventPayload payload = new UserEventPayload();
            payload.setUserId(String.valueOf(user.getUser_id()));
            payload.setEmail(user.getEmail());
            payload.setFullName(user.getUsername());
            kafkaService.sendUserRegisteredEvent(payload);

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