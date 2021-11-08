package com.fintech.chatbot_spring.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import com.fintech.chatbot_spring.ChatBot.ChatBot;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.util.HashMap;


@Controller
public class ChatBotController {
    
    private final ChatBot ChatBot;
    
    @Autowired 
    public ChatBotController(ChatBot ChatBot) {
        this.ChatBot = ChatBot;
    }

    @MessageMapping("/chatbot.join")
    @SendTo("/chatroom/public")
    public HashMap<String, String> addNewUser(HashMap<String, String> Message) {
        Message.put("content", ChatBot.sayHello());
        Message.put("userName", String.format("손님%d", ChatBot.getUserNum()));
        System.out.format("%s\n", Message);
        return Message;
    }

    @MessageMapping("/chatbot.sendMessage")
    @SendTo("/chatroom/public")
    public HashMap<String, String> sendMessage(HashMap<String, String> Message) {
        System.out.println("sendMessage" + Message);
        return Message;
    }
} 