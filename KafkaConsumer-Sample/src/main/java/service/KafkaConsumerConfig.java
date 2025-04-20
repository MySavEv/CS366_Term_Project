package service;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.apache.kafka.common.serialization.StringDeserializer;

//import com.fasterxml.jackson.databind.deser.std.StringDeserializer;

@EnableKafka
@Configuration  
public class KafkaConsumerConfig {
	@Bean
    public ConsumerFactory<String, String> consumerFactory() { 
  
        // Creating a Map of string-object pairs 
        Map<String, Object> config = new HashMap<String, Object>(); 
  
        // Adding the Configuration 
        //config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "[::1]:9092"); 
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "kafka:9092"); 
        config.put(ConsumerConfig.GROUP_ID_CONFIG, "my-group"); 
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class); 
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class); 
  
        return new DefaultKafkaConsumerFactory<>(config); 
    } 
	
	
	 // Creating a Listener 
    public ConcurrentKafkaListenerContainerFactory<String, String> 
    concurrentKafkaListenerContainerFactory() { 
        ConcurrentKafkaListenerContainerFactory<String, String> factory 
            = new ConcurrentKafkaListenerContainerFactory<String, String>(); 
        factory.setConsumerFactory(consumerFactory()); 
        return factory; 
    } 
}
