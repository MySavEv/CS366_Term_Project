package com.cs366.notification.kafka;


import org.springframework.kafka.annotation.KafkaListener;

import com.cs366.notification.event.OrderDetailEvent;
import com.cs366.notification.event.RiderAssignedEvent;
import com.cs366.notification.event.RiderDeliveredFoodEvent;
import com.cs366.notification.event.RiderPickedUpFoodEvent;
import com.cs366.notification.event.UserEventPayload;
import com.cs366.notification.service.NotiService;

public class KafkaConsumer {
    @KafkaListener(topics = "user.registered", containerFactory = "userKafkaListenerFactory")
    public void userRegister(UserEventPayload payload) {
        // Print statement
        NotiService.sendNoti(payload.getFullName() + "Registered");
    }

    @KafkaListener(topics = "user.registered", containerFactory = "userKafkaListenerFactory")
    public void userLogin(UserEventPayload payload) {
        // Print statement
        NotiService.sendNoti(payload.getFullName() + "Login!");
    }

    @KafkaListener(topics = "rider-assigned", containerFactory = "riderAssignedKafkaListenerFactory")
    public void riderAssigned(RiderAssignedEvent payload) {
        // Print statement
        NotiService.sendNoti(payload.getRiderId() + "Login!");
    }

    @KafkaListener(topics = "rider-delivered", containerFactory = "riderDeliveredKafkaListenerFactory")
    public void riderDelivered(RiderDeliveredFoodEvent payload) {
        // Print statement
        NotiService.sendNoti(payload.getRiderId() + "Login!");
    }

    @KafkaListener(topics = "rider-picked-up", containerFactory = "riderPickedKafkaListenerFactory")
    public void riderPicked(RiderPickedUpFoodEvent payload) {
        // Print statement
        NotiService.sendNoti(payload.getRiderId() + "Login!");
    }

    @KafkaListener(topics = "noti.orderdetail", containerFactory = "riderPickedKafkaListenerFactory")
    public void orderDetailToChef(OrderDetailEvent payload) {
        // Print statement
        NotiService.sendNoti(payload.getNote() + "Login!");
    }
}
