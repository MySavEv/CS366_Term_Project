
package com.cs366.restaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cs366.restaurant.model.OrderDetail;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {

}
