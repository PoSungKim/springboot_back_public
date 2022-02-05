package com.fintech.chatbot_spring.Dao;

import org.aspectj.lang.annotation.After;
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
    MessageRepository messageRepository;

    @Test
    @Order(2)
    public void cleanup() {
        messageRepository.deleteAll();
    }

    @Test
    @Order(1)
    public void testSaveThenFind() {
        Long id = (long)1;
        String content = "첫 번째 메세지";

        messageRepository.save(Message.builder().id(id).content(content).build());

        List<Message> messageList = messageRepository.findAll();
        logger.info(messageList.toString());

        Message message = messageList.get(0);
        assertThat(message.getId()).isEqualTo(id);
        assertThat(message.getContent()).isEqualTo(content);

    }
}