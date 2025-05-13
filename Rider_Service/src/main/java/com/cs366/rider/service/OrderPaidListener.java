package com.cs366.rider.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;


@Service
public class OrderPaidListener {
    @Autowired
    private RiderService riderService;

    

    @KafkaListener(topics = "orderpaid", groupId = "rider-group", containerFactory = "kafkaListenerContainerFactory")
    public void handleOrderPaid(String message) {
        System.out.println("📦 Received OrderPaid Event: Order ID " + message);

        // Mock หา Rider
        riderService.assignRider(message);
    }
}