package com.fintech.chatbot_spring.Controller;

import com.fintech.chatbot_spring.Service.ChatbotService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fintech.chatbot_spring.Service.KafkaProducerService;
import com.fintech.chatbot_spring.ChatBot.ChatBot;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import java.util.HashMap;

@Api(tags = {"Chatbot Controller"})
@RestController
public class ChatBotController {

    private static final Logger logger = LoggerFactory.getLogger(ChatBotController.class);

    private final ChatBot ChatBot;
    private final KafkaProducerService KafkaProducerService;
    private final ChatbotService chatbotService;

    @Autowired 
    public ChatBotController(ChatBot ChatBot, KafkaProducerService KafkaProducerService, ChatbotService chatbotService) {
        this.ChatBot = ChatBot;
        this.KafkaProducerService = KafkaProducerService;
        this.chatbotService = chatbotService;
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
    public HashMap<String, String> sendMessage(HashMap<String, String> message) {

        logger.info(String.format("%s 대화가 입력되었습니다 : %s", message.get("userName"), message.get("content")));

        if (message.get("userName").equals("")) {
            message.put("userName", "SOL");
            message.put("content", "다시 한번 보내주세요 ✨");
        }

        try {
            chatbotService.saveMessage(message);
        } catch(Exception e) {
            logger.info(""+e);
        }

        try {
            //KafkaProducerService.sendMessage(Message.toString());
        } catch (Exception e) {
            logger.info(""+e);
        }
        
        return message;
    }
} 