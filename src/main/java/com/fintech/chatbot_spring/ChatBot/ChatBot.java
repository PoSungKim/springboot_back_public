package com.fintech.chatbot_spring.ChatBot;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@Scope("singleton")
public class ChatBot {
    int userNum;

    public String sayHello() {
        return String.format("안녕하세요 %d번 손님! 환영합니다 🍀", ++userNum);
    }
}
