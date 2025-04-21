package com.cs366.rider.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;


@Service
public class OrderPaidListener {

    @KafkaListener(topics = "order-paid", groupId = "rider-group", containerFactory = "kafkaListenerContainerFactory")
    public void handleOrderPaid(String message) {
        System.out.println("📦 Received OrderPaid Event: Order ID " + message);

        // Mock หา Rider
        String rider = findAvailableRider();
        System.out.println("🛵 Assigned Rider: " + rider + " for Order " + message);
    }

    private String findAvailableRider() {
        // Mock logic หา driver
        return "Rider#" + (int)(Math.random() * 1000);
    }
}