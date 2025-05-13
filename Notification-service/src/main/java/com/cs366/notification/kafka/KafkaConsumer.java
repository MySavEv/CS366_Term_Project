package com.cs366.notification.kafka;


import org.springframework.kafka.annotation.KafkaListener;

import com.cs366.notification.event.UserEventPayload;
import com.cs366.notification.service.NotiService;

public class KafkaConsumer {
    @KafkaListener(topics = "user.registered")
    public void userRegister(UserEventPayload payload) {
        // Print statement
        NotiService.sendNoti(payload.getFullName() + "Registered");
    }

    @KafkaListener(topics = "user.registered")
    public void userLogin(UserEventPayload payload) {
        // Print statement
        NotiService.sendNoti(payload.getFullName() + "Login!");
    }
}
