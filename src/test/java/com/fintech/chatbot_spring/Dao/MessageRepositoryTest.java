package com.fintech.chatbot_spring.Dao;

import com.fintech.chatbot_spring.Domain.User;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.fintech.chatbot_spring.Domain.Message;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class MessageRepositoryTest {

    private static final Logger logger = LoggerFactory.getLogger(MessageRepositoryTest.class);

    @Autowired
    UserRepository userRepository;

    @Autowired
    MessageRepository messageRepository;

    @Test
    void userMessageOneToManyManyToOneRelation() {
        User user = userRepository.findByEmail("Ryan@test.com");

        Message message = new Message();
        message.setContent("First message from Ryan");
        message.setUser(user);
        messageRepository.save(message);

        message = new Message();
        message.setContent("Second message from Ryan");
        message.setUser(user);
        messageRepository.save(message);

        message = new Message();
        message.setContent("Third message from Ryan");
        message.setUser(user);
        messageRepository.save(message);

        //User Table : OneToMany
        user = userRepository.findByEmail("Ryan@test.com");
        logger.info("user.getMessages()... : " + user.getMessages());

        //Message Table : ManyToOne
        message = messageRepository.findById(1L).orElseThrow(RuntimeException::new);
        logger.info("message.getUser()... : " + message.getUser());
    }

    @Test
    @Order(2)
    public void cleanup() {
        messageRepository.deleteAll();
    }

    @Test
    @Order(1)
    public void testSaveThenFind() {

        Long id = 1L;
        String content = "첫 번째 메세지";

        messageRepository.save(Message
                .builder()
                .id(id)
                .content(content)
                .build()
        );

        List<Message> messageList = messageRepository.findAll();
        logger.info(messageList.toString());

        Message message = messageList.get(0);
        assertThat(message.getId()).isEqualTo(id);
        assertThat(message.getContent()).isEqualTo(content);

    }
}