package com.cs366.notification.kafka;


import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.cs366.customer.dto.UserEventPayload;
import com.cs366.notification.event.OrderDetailEvent;
import com.cs366.notification.event.PaymentEvent;
import com.cs366.notification.event.RiderAssignedEvent;
import com.cs366.notification.event.RiderDeliveredFoodEvent;
import com.cs366.notification.event.RiderPickedUpFoodEvent;
import com.cs366.notification.service.NotiService;

@Component
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

    @KafkaListener(topics = "noti.orderdetail", containerFactory = "orderDetailToChefKafkaListenerFactory")
    public void orderDetailToChef(OrderDetailEvent payload) {
        // Print statement
        NotiService.sendNoti(payload.getNote() + "Login!");
    }

    @KafkaListener(topics = "payment-events", containerFactory = "paymentKafkaListenerFactory")
    public void paymentEvent(PaymentEvent payload) {
        // Print statement
        NotiService.sendNoti(payload.getOrderId() + "Login!");
    }

}
