package com.cs366.notification.config;


import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.cs366.notification.event.OrderDetailEvent;
import com.cs366.notification.event.RiderAssignedEvent;
import com.cs366.notification.event.RiderDeliveredFoodEvent;
import com.cs366.notification.event.RiderPickedUpFoodEvent;
import com.cs366.notification.event.UserEventPayload;


@Configuration
@EnableKafka
public class KafkaConsumerConfig {

    private final Map<String,Object> props = new HashMap<>();

    KafkaConsumerConfig(){
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "noti-group");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        props.put(JsonDeserializer.TRUSTED_PACKAGES, "com.cs366.notification.event"); // หรือระบุเฉพาะ package ที่ใช้งาน
    }

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, UserEventPayload> userKafkaListenerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, UserEventPayload> factory =
            new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(new DefaultKafkaConsumerFactory<>(
            props,
            new StringDeserializer(),
            new JsonDeserializer<>(UserEventPayload.class)
        ));
        return factory;
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, RiderAssignedEvent> riderAssignedKafkaListenerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, RiderAssignedEvent> factory =
            new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(new DefaultKafkaConsumerFactory<>(
            props,
            new StringDeserializer(),
            new JsonDeserializer<>(RiderAssignedEvent.class)
        ));
        return factory;
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, RiderDeliveredFoodEvent> riderDeliveredKafkaListenerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, RiderDeliveredFoodEvent> factory =
            new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(new DefaultKafkaConsumerFactory<>(
            props,
            new StringDeserializer(),
            new JsonDeserializer<>(RiderDeliveredFoodEvent.class)
        ));
        return factory;
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, RiderPickedUpFoodEvent> riderPickedKafkaListenerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, RiderPickedUpFoodEvent> factory =
            new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(new DefaultKafkaConsumerFactory<>(
            props,
            new StringDeserializer(),
            new JsonDeserializer<>(RiderPickedUpFoodEvent.class)
        ));
        return factory;
    }

        @Bean
    public ConcurrentKafkaListenerContainerFactory<String, OrderDetailEvent> orderDetailToChefKafkaListenerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, OrderDetailEvent> factory =
            new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(new DefaultKafkaConsumerFactory<>(
            props,
            new StringDeserializer(),
            new JsonDeserializer<>(OrderDetailEvent.class)
        ));
        return factory;
    }


}
