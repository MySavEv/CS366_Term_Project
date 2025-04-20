package service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {
	@KafkaListener(topics = "test-topic", groupId = "my-group")

	public void consume(String message) {
		// Print statement
		System.out.println("message = " + message);
	}
}
