package com.fintech.chatbot_spring.Service;

import com.fintech.chatbot_spring.Dao.MessageRepository;
import com.fintech.chatbot_spring.Dao.UserRepository;
import com.fintech.chatbot_spring.Domain.Message;
import com.fintech.chatbot_spring.Domain.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ChatbotService {

    private final MessageRepository messageRepository;
    private final UserRepository userRepository;

    public ChatbotService(MessageRepository messageRepository, UserRepository userRepository) {
        this.messageRepository = messageRepository;
        this.userRepository = userRepository;
    }

    public void saveMessage(Map<String, String> message) {

        Message msg = new Message();
        msg.setContent(message.get("content"));

        List<User> userList = userRepository.findByName(message.get("userName"));
        User user;
        if (userList.size() == 0) {
            user = new User();
            user.setName(message.get("userName"));
            user.setEmail(message.get("userName") + "@test.com");
            userRepository.save(user);
        } else {
            user = userList.get(0);
        }

        msg.setUser(user);
        messageRepository.save(msg);
    }
}
