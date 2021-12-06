package com.fintech.chatbot_spring.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/websocketConnection")
                .setAllowedOrigins("http://localhost:81", "https://posungkim.github.io", "ip-172-31-1-81.ap-northeast-2.compute.internal")
                .withSockJS();
    }
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.setApplicationDestinationPrefixes("/");        // /으로 시작하는 요청은 ChatBotController를 통한다
        registry.enableSimpleBroker("/chatroom","/queue");         // in-memory broker
        
    }
}