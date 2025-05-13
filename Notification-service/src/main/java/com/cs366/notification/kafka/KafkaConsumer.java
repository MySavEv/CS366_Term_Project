package main.java.com.cs366.notification;

import main.java.com.cs366.notification.service.NotiService;

public class KafkaConsumer {
    @KafkaListener(topics = "OrderCreate", groupId = "2")
    public void orderCreate(String message) {
        // Print statement
        NotiService.sendNoti(message);
    }

    @KafkaListener(topics = "OrderPaid", groupId = "2")
    public void OrderPaid(String message) {
        // Print statement
        NotiService.sendNoti(message);
    }

    @KafkaListener(topics = "OrderReady", groupId = "2")
    public void OrderReady(String message) {
        // Print statement
        NotiService.sendNoti(message);
    }

    @KafkaListener(topics = "OrderDelivered", groupId = "2")
    public void OrderDelivered(String message) {
        // Print statement
        NotiService.sendNoti(message);
    }
}
