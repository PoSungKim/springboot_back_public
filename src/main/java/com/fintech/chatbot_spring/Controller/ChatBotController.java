package com.fintech.chatbot_spring.Controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import java.util.HashMap;

@Controller
public class ChatBotController {
    
    @MessageMapping("/addUser")
    @SendTo("/topic/public")
    public HashMap<String, String> broadCast(HashMap<String, String> Message) {
        
        System.out.println(Message);
        return Message;
    }
}
