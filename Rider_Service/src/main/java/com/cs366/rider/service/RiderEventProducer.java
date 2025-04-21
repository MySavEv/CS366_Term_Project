package com.cs366.rider.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.cs366.rider.dto.RiderAssignedEvent;
import com.cs366.rider.dto.RiderDeliveredFoodEvent;
import com.cs366.rider.dto.RiderPickedUpFoodEvent;

@Service
public class RiderEventProducer {

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    public void sendRiderAssigned(RiderAssignedEvent event) {
        kafkaTemplate.send("rider-assigned", event);
    }

    public void sendPickedUp(RiderPickedUpFoodEvent event) {
        kafkaTemplate.send("rider-picked-up", event);
    }

    public void sendDelivered(RiderDeliveredFoodEvent event) {
        kafkaTemplate.send("rider-delivered", event);
    }
}