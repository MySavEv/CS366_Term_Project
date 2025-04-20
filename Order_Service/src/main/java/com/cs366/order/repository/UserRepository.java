package com.cs366.order.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cs366.order.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);
}