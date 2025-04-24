package com.cs366.restaurant.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.cs366.restaurant.event.OrderDetailEvent;

@Service
public class KafkaProducerService {

    @Autowired
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private final KafkaTemplate<String, Object> kafkaJsonTemplate;


    public void sendUserEvent(OrderDetailEvent event) {
        kafkaJsonTemplate.send("user-events-topic", event);
    }

}