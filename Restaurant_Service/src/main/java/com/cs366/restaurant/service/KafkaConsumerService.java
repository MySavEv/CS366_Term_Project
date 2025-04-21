package com.cs366.restaurant.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;

public class KafkaConsumerService {
    @Autowired
    private KafkaProducerService kafkaProducerService;

    @KafkaListener(topics = "OrderPaid", groupId = "my-group")
    public void consume(String message) {
        // Print statement
        System.out.println("foodPrePare");

        kafkaProducerService.sendMessage("orderReciveByRest", "true");
    }
}
