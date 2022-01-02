package com.fintech.chatbot_spring.Dao;

import com.fintech.chatbot_spring.Controller.ChatBotController;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.fintech.chatbot_spring.Domain.Message;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MessageRepositoryTest {

    private static final Logger logger = LoggerFactory.getLogger(ChatBotController.class);

    @Autowired
    MessageRepository messageRepository;

    @After
    public void cleanup() {
        messageRepository.deleteAll();
    }

    @Test
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