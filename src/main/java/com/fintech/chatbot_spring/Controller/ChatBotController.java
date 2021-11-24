package com.fintech.chatbot_spring.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import com.fintech.chatbot_spring.ChatBot.ChatBot;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;


@RestController
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
        Message.put("userName", String.format("손님%d\n", ChatBot.getUserNum()));
        Message.put("meta-info", "ChatBot");
        return Message;
    }

    @MessageMapping("/chatbot.sendMessage")
    @SendTo("/chatroom/public")
    public HashMap<String, String> sendMessage(HashMap<String, String> Message) {
        if (Message.get("userName").equals("")) {
            Message.put("userName", "SOL");
            Message.put("content", "다시 한번 보내주세요 ✨");
        }
        
        return Message;
    }
} 