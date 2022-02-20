package com.fintech.chatbot_spring.Controller;

import com.fintech.chatbot_spring.Domain.User;
import com.fintech.chatbot_spring.Service.ChatbotService;
import com.fintech.chatbot_spring.Service.UserService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
    private final UserService userService;

    @Autowired
    public ChatBotController(ChatBot ChatBot, KafkaProducerService KafkaProducerService, ChatbotService chatbotService, UserService userService) {
        this.ChatBot = ChatBot;
        this.KafkaProducerService = KafkaProducerService;
        this.chatbotService = chatbotService;
        this.userService = userService;
    }

    @MessageMapping("/chatbot.join")
    @SendTo("/chatroom/public")
    public HashMap<String, String> addNewUser(HashMap<String, String> message) {
        message.put("content", ChatBot.sayHello());
        message.put("userName", String.format("손님%d", ChatBot.getUserNum()));
        message.put("meta-info", "ChatBot");

        try {
            User user = new User();
            user.setName(message.get("userName"));
            user.setEmail(message.get("userName") + "@test.com");
            userService.addUser(user);
        } catch (Exception e) {
            logger.info(e.getMessage());
        }

        logger.info(String.format("새로운 손님이 입장하셨습니다 : (%s)", message.get("userName")));

        return message;
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