package com.cs366.order.service;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import com.cs366.order.event.OrderCreatedEvent;
import com.cs366.order.event.OrderDetailEvent;
import com.cs366.order.model.Order;

@Component
public class OrderProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final KafkaTemplate<String,String> kafkaStringTemplate;

    public OrderProducer(KafkaTemplate<String, Object> kafkaTemplate,KafkaTemplate<String,String> kafkaStringTemplate) {
        this.kafkaTemplate = kafkaTemplate;
        this.kafkaStringTemplate = kafkaStringTemplate;
    }

    public void sendOrderCreated(Order order) {

        OrderCreatedEvent oce = new OrderCreatedEvent();

        oce.setEventType("orderCreated");
        oce.setOrderId(String.valueOf(order.getOrderId()));
        oce.setAmount(1);
        oce.setTimestamp(null);

        kafkaTemplate.send("order_created", oce);

    }

    public void findingRider(String orderid) {

        kafkaStringTemplate.send("orderpaid", orderid);

    }

    public void sendOrderDetail(OrderDetailEvent payload) {
        kafkaTemplate.send("orderdetail", payload);

    }
}
