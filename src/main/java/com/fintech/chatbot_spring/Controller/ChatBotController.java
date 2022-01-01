package com.fintech.chatbot_spring.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.fintech.chatbot_spring.Service.KafkaProducerService;
import com.fintech.chatbot_spring.ChatBot.ChatBot;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import java.util.HashMap;

@RestController
public class ChatBotController {

    private static final Logger logger = LoggerFactory.getLogger(ChatBotController.class);

    private final ChatBot ChatBot;
    private final KafkaProducerService KafkaProducerService;

    @Autowired 
    public ChatBotController(ChatBot ChatBot, KafkaProducerService KafkaProducerService) {
        this.ChatBot = ChatBot;
        this.KafkaProducerService = KafkaProducerService;
    }

    @MessageMapping("/chatbot.join")
    @SendTo("/chatroom/public")
    public HashMap<String, String> addNewUser(HashMap<String, String> Message) {
        Message.put("content", ChatBot.sayHello());
        Message.put("userName", String.format("손님%d", ChatBot.getUserNum()));
        Message.put("meta-info", "ChatBot");

        logger.info(String.format("새로운 손님이 입장하셨습니다 : (%s)", Message.get("userName")));

        return Message;
    }

    @MessageMapping("/chatbot.sendMessage")
    @SendTo("/chatroom/public")
    public HashMap<String, String> sendMessage(HashMap<String, String> Message) {

        logger.info(String.format("손님 대화가 입력되었습니다 : (%s)", Message.get("userName")));

        if (Message.get("userName").equals("")) {
            Message.put("userName", "SOL");
            Message.put("content", "다시 한번 보내주세요 ✨");
        }

        try {
            //KafkaProducerService.sendMessage(Message.toString());
        } catch (Exception e) {
            System.out.println(e);
        }
        
        return Message;
    }
} 