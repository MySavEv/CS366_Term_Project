package com.cs366.customer.service;

import java.time.Instant;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.cs366.customer.dto.UserEventPayload;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaProducerService {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final KafkaTemplate<String, Object> kafkaJsonTemplate;

    public void sendUserRegisteredEvent(UserEventPayload payload) {
        sendEvent("user.registered", payload);
    }

    public void sendUserLoggedInEvent(UserEventPayload payload) {
        sendEvent("user.loggedin", payload);
    }

    private void sendEvent(String topic, UserEventPayload payload) {
        try {
            payload.setEventTime(Instant.now().toString());
            payload.setEventType(topic);

            kafkaJsonTemplate.send(topic, payload);
            log.info("✅ Sent event to Kafka: topic={} userId={}", topic, payload);

        } catch (Exception e) {
            log.error("❌ Failed to send Kafka event to topic={}", topic, e);
        }
    }
}
