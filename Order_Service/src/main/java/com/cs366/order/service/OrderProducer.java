package com.cs366.order.service;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import com.cs366.order.event.OrderCreatedEvent;
import com.cs366.order.model.Order;

@Component
public class OrderProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public OrderProducer(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendOrderCreated(Order order) {

        OrderCreatedEvent oce = new OrderCreatedEvent();

        oce.setEventType("orderCreated");
        oce.setOrderId(String.valueOf(order.getOrderId()));
        oce.setAmount(1);
        oce.setTimestamp(null);

        kafkaTemplate.send("order_created", oce);

    }
}
