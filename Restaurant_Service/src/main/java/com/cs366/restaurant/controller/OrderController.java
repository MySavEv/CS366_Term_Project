package com.cs366.restaurant.controller;

import com.cs366.restaurant.dto.OrderStatusUpdate;
import com.cs366.restaurant.model.Order;
import com.cs366.restaurant.repository.OrderRepository;
import com.cs366.restaurant.service.KafkaProducerService;
import com.cs366.restaurant.utils.ResponseHandler;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/restaurant/order")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private KafkaProducerService kafkaProducerService;

    @PostMapping("/ready")
    public ResponseEntity<?> updateOrderStatus(@RequestBody OrderStatusUpdate statusUpdate) {
        Optional<Order> order = orderRepository.findById((long) Integer.parseInt(statusUpdate.getOrderId()));
        Order or = order.get();
        or.setStatus("FOOD_READY");
        Order result = orderRepository.save(or);

        kafkaProducerService.sendMessage("Foodprepared", String.valueOf(result.getOrderId()));

        return ResponseHandler.generateResponse("Order status updated", true, result);
    }
}
