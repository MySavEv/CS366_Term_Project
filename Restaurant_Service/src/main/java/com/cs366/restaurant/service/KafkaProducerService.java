package com.cs366.restaurant.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.cs366.restaurant.event.OrderDetailEvent;
import com.cs366.restaurant.event.OrderDetailEvent.OrderedItem;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaProducerService {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final KafkaTemplate<String, Object> kafkaJsonTemplate;

    public void sendMockOrder() {
        OrderDetailEvent mockEvent = new OrderDetailEvent("10",
            "resto-001",
            "Save",
            List.of(
                new OrderedItem("menu-101", "ผัดกระเพรา", 2),
                new OrderedItem("menu-202", "ไข่เจียว", 1)
            ),
            LocalDateTime.now(),
            "ไม่ใส่พริก"
        );

        sendEvent("orderpaid", mockEvent);
        System.out.println("✅ ส่ง mock order ไปยัง Kafka เรียบร้อย");
    }

    public void sendNotiChefEvent(OrderDetailEvent payload) {
        sendEvent("noti.orderdetail", payload);
    }

    public void sendFoodPreparedEvent(String orderId) {
        sendEvent("food.prepared", orderId);
    }

    private void sendEvent(String topic, Object payload) {
        try {
            kafkaJsonTemplate.send(topic, payload);
            log.info("✅ Sent event to Kafka: topic={} userId={}", topic, payload);

        } catch (Exception e) {
            log.error("❌ Failed to send Kafka event to topic={}", topic, e);
        }
    }

    private void sendEvent(String topic, String message) {
        try {
            kafkaTemplate.send(topic, message);
            log.info("✅ Sent event to Kafka: topic={} userId={}", topic, message);

        } catch (Exception e) {
            log.error("❌ Failed to send Kafka event to topic={}", topic, e);
        }
    }
}