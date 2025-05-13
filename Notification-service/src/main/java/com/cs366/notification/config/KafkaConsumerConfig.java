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
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.cs366.notification.event.OrderDetailEvent;
import com.cs366.notification.event.PaymentEvent;
import com.cs366.notification.event.RiderAssignedEvent;
import com.cs366.notification.event.RiderDeliveredFoodEvent;
import com.cs366.notification.event.RiderPickedUpFoodEvent;
import com.cs366.notification.event.UserEventPayload;


@Configuration
@EnableKafka
public class KafkaConsumerConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;
    


    private Map<String, Object> consumerConfigs(JsonDeserializer<?> des) {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "noti-group");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, des.getClass());
        return props;
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, UserEventPayload> userKafkaListenerFactory() {
        JsonDeserializer<UserEventPayload> deserializer = new JsonDeserializer<>(UserEventPayload.class);
        deserializer.setRemoveTypeHeaders(false);
        deserializer.addTrustedPackages("*");
        deserializer.setUseTypeMapperForKey(true);

        ConcurrentKafkaListenerContainerFactory<String, UserEventPayload> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(
            new DefaultKafkaConsumerFactory<>(
                consumerConfigs(deserializer),
                new StringDeserializer(),
                deserializer
            ));
        return factory;
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, RiderAssignedEvent> riderAssignedKafkaListenerFactory() {
        JsonDeserializer<RiderAssignedEvent> deserializer = new JsonDeserializer<>(RiderAssignedEvent.class);
        deserializer.setRemoveTypeHeaders(false);
        deserializer.addTrustedPackages("*");
        deserializer.setUseTypeMapperForKey(true);

        ConcurrentKafkaListenerContainerFactory<String, RiderAssignedEvent> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(
            new DefaultKafkaConsumerFactory<>(
                consumerConfigs(deserializer),
                new StringDeserializer(),
                deserializer
            ));
        return factory;
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, RiderDeliveredFoodEvent> riderDeliveredKafkaListenerFactory() {
        JsonDeserializer<RiderDeliveredFoodEvent> deserializer = new JsonDeserializer<>(RiderDeliveredFoodEvent.class);
        deserializer.setRemoveTypeHeaders(false);
        deserializer.addTrustedPackages("*");
        deserializer.setUseTypeMapperForKey(true);

        ConcurrentKafkaListenerContainerFactory<String, RiderDeliveredFoodEvent> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(
            new DefaultKafkaConsumerFactory<>(
                consumerConfigs(deserializer),
                new StringDeserializer(),
                deserializer
            ));
        return factory;
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, RiderPickedUpFoodEvent> riderPickedKafkaListenerFactory() {
        JsonDeserializer<RiderPickedUpFoodEvent> deserializer = new JsonDeserializer<>(RiderPickedUpFoodEvent.class);
        deserializer.setRemoveTypeHeaders(false);
        deserializer.addTrustedPackages("*");
        deserializer.setUseTypeMapperForKey(true);

        ConcurrentKafkaListenerContainerFactory<String, RiderPickedUpFoodEvent> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(
            new DefaultKafkaConsumerFactory<>(
                consumerConfigs(deserializer),
                new StringDeserializer(),
                deserializer
            ));
        return factory;
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, OrderDetailEvent> orderDetailToChefKafkaListenerFactory() {
        JsonDeserializer<OrderDetailEvent> deserializer = new JsonDeserializer<>(OrderDetailEvent.class);
        deserializer.setRemoveTypeHeaders(false);
        deserializer.addTrustedPackages("*");
        deserializer.setUseTypeMapperForKey(true);

        ConcurrentKafkaListenerContainerFactory<String, OrderDetailEvent> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(
            new DefaultKafkaConsumerFactory<>(
                consumerConfigs(deserializer),
                new StringDeserializer(),
                deserializer
            ));
        return factory;
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, PaymentEvent> paymentKafkaListenerFactory() {
        JsonDeserializer<PaymentEvent> deserializer = new JsonDeserializer<>(PaymentEvent.class);
        deserializer.setRemoveTypeHeaders(false);
        deserializer.addTrustedPackages("*");
        deserializer.setUseTypeMapperForKey(true);

        ConcurrentKafkaListenerContainerFactory<String, PaymentEvent> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(
            new DefaultKafkaConsumerFactory<>(
                consumerConfigs(deserializer),
                new StringDeserializer(),
                deserializer
            ));
        return factory;
    }


}
