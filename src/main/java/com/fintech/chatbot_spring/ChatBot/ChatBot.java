package com.fintech.chatbot_spring.ChatBot;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@Scope("prototype")
public class ChatBot {
    private static long userNum = 0;

    public static long getUserNum() {
        return userNum;
    }

    public String sayHello() {
        return String.format("안녕하세요 %d번 손님! 환영합니다 🍀", ++userNum);
    }
}
