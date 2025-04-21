package com.cs366.restaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cs366.restaurant.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
