package com.fintech.chatbot_spring.Service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {
    @KafkaListener(topics = "chatbot", groupId = "chatbotGroup")
    public void consume(String message) {
        System.out.printf("This is Consumer; the message is %s\n", message);
    }
}