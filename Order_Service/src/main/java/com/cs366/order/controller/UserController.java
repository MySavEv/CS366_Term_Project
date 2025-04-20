package com.cs366.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cs366.order.model.User;
import com.cs366.order.service.UserService;
import com.cs366.order.utils.ResponseHandler;


@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserService userService;

    // ✅ GET: Find All Users
    @GetMapping("/users")
    public ResponseEntity<Object> findAllUsers() {
        try {
            return ResponseHandler.generateResponse("Users retrieved successfully", true, userService.findAll());
        } catch (Exception e) {
            return ResponseHandler.generateResponse("Error fetching users", false, e.getMessage());
        }
    }

    // ✅ GET: Get Profile (By Authenticated User ID)
    @GetMapping("/user/{id}")
    public ResponseEntity<Object> getProfile(@PathVariable("id") Long userId) {
        try {
            return ResponseHandler.generateResponse("User profile", true, userService.findById(userId));
        } catch (Exception e) {
            return ResponseHandler.generateResponse("User not found", false, e.getMessage());
        }
    }

    // ✅ PUT: Update User
    @PutMapping("/user/{id}")
    public ResponseEntity<Object> updateUser(@PathVariable("id") Long userId, @RequestBody User userUpdate) {
        try {
            User updatedUser = userService.updateUser(userId, userUpdate);
            return ResponseHandler.generateResponse("User updated successfully", true, updatedUser);
        } catch (Exception e) {
            return ResponseHandler.generateResponse("Update failed", false, e.getMessage());
        }
    }

    // ✅ DELETE: Delete User
    @DeleteMapping("/user/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable("id") Long userId) {
        try {
            boolean deleted = userService.deleteUser(userId);
            return ResponseHandler.generateResponse("User deleted", true, deleted);
        } catch (Exception e) {
            return ResponseHandler.generateResponse("User not found or cannot delete", false, e.getMessage());
        }
    }
}
