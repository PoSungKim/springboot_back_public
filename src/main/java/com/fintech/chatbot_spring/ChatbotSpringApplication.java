package com.fintech.chatbot_spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class ChatbotSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChatbotSpringApplication.class, args);
	}

}
