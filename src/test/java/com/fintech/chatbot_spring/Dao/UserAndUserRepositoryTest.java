package com.fintech.chatbot_spring.Dao;

import com.fintech.chatbot_spring.Domain.Message;
import com.fintech.chatbot_spring.Domain.User;
import com.fintech.chatbot_spring.Domain.UserAndUser;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Propagation;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserAndUserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserAndUserRepository userAndUserRepository;

    @Autowired
    MessageRepository messageRepository;

    @Test
    public void createMessage() {
        Message message = new Message();
        message.setContent("This message is from Ryan to Brian");
        messageRepository.save(message);
    }

    @Test
    @Transactional
    public void createRelation() {
        // FK로 연결해줄 Row가 같은 @Transactional에서
        User user1 = userRepository.findById(1L).orElseThrow(RuntimeException::new);
        User user2 = userRepository.findById(2L).orElseThrow(RuntimeException::new);
        createMessage();

        Message message = messageRepository.getById(1L);

        UserAndUser userAndUser = new UserAndUser();
        userAndUser.setFromUser(user1);
        userAndUser.setToUser(user2);
        userAndUser.setMessage(message);
        userAndUserRepository.save(userAndUser);
//        message.getUserAndUsers().add(userAndUser);

        System.out.println(userAndUserRepository.findById(1L).orElseThrow(RuntimeException::new));

        user1 = userRepository.findById(1L).orElseThrow(RuntimeException::new);
        // Correct Usage
        System.out.println("user1.getFromUsers() : " + user1.getFromUsers());
        // Wrong Usage : supposed to be an empty list
        System.out.println("user1.getToUsers() : " + user1.getToUsers());

        user2 = userRepository.findById(2L).orElseThrow(RuntimeException::new);
        // Correct Usage
        System.out.println("user2.getToUsers() : " + user2.getToUsers());
        // Wrong Usage : supposed to be an empty list
        System.out.println("user2.getFromUsers() : " + user2.getFromUsers());

        message = messageRepository.findById(1L).orElseThrow(RuntimeException::new);
        System.out.println("message.getUserAndUsers() : " + message.getUserAndUsers());
    }
}