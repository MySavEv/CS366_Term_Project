package com.example.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/kafka")
public class KafkaController {
	@Autowired
    private final KafkaProducerService producerService;

    public KafkaController(KafkaProducerService producerService) {
        this.producerService = producerService;
    }

    @GetMapping("/send")
    public String sendMessage(@RequestParam String message) {
        String topic = "test-topic";
        producerService.sendMessage(topic, message);
        return "Message:" + message + " sent to Kafka topic: " + topic;
    }
    
    @PostMapping(value = "/postItem",consumes = {"application/json"})
    public String postJsonMessage(@RequestBody Item item){
        //KafkaJsontemplate.send(TOPIC_NAME,new Item(1,"Lenovo","Laptop"));
        return "Message: "+ item.toString() + "published successfully";
    }
}
