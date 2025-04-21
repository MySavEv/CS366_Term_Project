package com.cs366.order.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import com.cs366.order.model.Order;

@Component
public class OrderProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    public OrderProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = new ObjectMapper();
        this.objectMapper.registerModule(new JavaTimeModule());
    }

    public void sendOrderCreated(Order order) {
        try {
            System.out.println("🔵 Preparing Kafka message...");
            String message = objectMapper.writeValueAsString(order);
            kafkaTemplate.send("order_created", message);
            System.out.println("✅ Sent to Kafka: " + message);
        } catch (JsonProcessingException e) {
            System.out.println("❌ JSON error: " + e.getMessage());
        }
    }
}
