package com.cs366.payment.service;

import com.cs366.payment.entity.Payment;
import com.cs366.payment.event.OrderCreatedEvent;
import com.cs366.payment.event.PaymentEvent;
import com.cs366.payment.repository.PaymentRepository;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class PaymentProcessor {

    private final KafkaTemplate<String, PaymentEvent> kafkaTemplate;
    private final PaymentRepository paymentRepository;

    public PaymentProcessor(KafkaTemplate<String, PaymentEvent> kafkaTemplate,
                            PaymentRepository paymentRepository) {
        this.kafkaTemplate = kafkaTemplate;
        this.paymentRepository = paymentRepository;
    }

    public void processPayment(OrderCreatedEvent order) {
        String paymentId = UUID.randomUUID().toString();
        PaymentEvent event = new PaymentEvent();
        event.setOrderId(order.getOrderId());
        event.setPaymentId(paymentId);
        event.setAmount(order.getAmount());
        event.setTimestamp(LocalDateTime.now());

        // จำลองสำเร็จ/ล้มเหลว
        if (Math.random() > 0.5) {
            event.setEventType("PaymentSuccess");
            event.setStatus("PAID");
        } else {
            event.setEventType("PaymentFailed");
            event.setStatus("FAILED");
            event.setReason("Insufficient funds");
        }

        // บันทึก DB
        Payment payment = new Payment();
        payment.setOrderId(order.getOrderId());
        payment.setPaymentId(paymentId);
        payment.setAmount(order.getAmount());
        payment.setStatus(event.getStatus());
        payment.setReason(event.getReason());
        payment.setTimestamp(event.getTimestamp());

        paymentRepository.save(payment);

        kafkaTemplate.send("payment-events", event);
    }
}
