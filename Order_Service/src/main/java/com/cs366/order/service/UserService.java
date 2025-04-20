package com.cs366.order.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cs366.order.model.User;
import com.cs366.order.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository; 

    // ดึง users ทั้งหมด
    public List<User> findAll() {
        return userRepository.findAll();
    }

    // ดึง user จาก ID
    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    // อัปเดต username และ role
    public User updateUser(Long id, User userUpdate) {
        User existingUser = findById(id);

        if (userUpdate.getUsername() != null && !userUpdate.getUsername().isEmpty()) {
            existingUser.setUsername(userUpdate.getUsername());
        }
        // Email/Password ไม่ควรอัปเดตตรงนี้ (ตาม logic เดิมใน Go)

        return userRepository.save(existingUser);
    }

    // ลบ user
    public boolean deleteUser(Long id) {
        User user = findById(id);
        userRepository.delete(user);
        return true;
    }
}