package com.cs366.payment.kafka;

import com.cs366.payment.model.OrderCreatedEvent;
import com.cs366.payment.service.PaymentProcessor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class OrderCreatedListener {

    private final PaymentProcessor processor;

    public OrderCreatedListener(PaymentProcessor processor) {
        this.processor = processor;
    }

    @KafkaListener(topics = "order_created", groupId = "payment-service")
    public void listen(OrderCreatedEvent event) {
        processor.processPayment(event);
    }
}
