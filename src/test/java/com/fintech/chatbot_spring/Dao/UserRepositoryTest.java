package com.fintech.chatbot_spring.Dao;

import com.fintech.chatbot_spring.Domain.User;
import org.junit.Before;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

//@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {
    private static final Logger logger = LoggerFactory.getLogger(MessageRepositoryTest.class);

    @Autowired
    UserRepository userRepository;

    @Test
    @Order(value = 1)
    void crudCreate() {
        userRepository.save(User.builder()
                .name("Josh")
                .email("test@test.com")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build());
    }

    @Test
    @Order(value = 2)
    void crudRead() {
        logger.info(userRepository.findById(1L).toString());
    }

    @Test
    @Order(value = 3)
    void crudUpdate() {

    }

    @Test
    @Order(value = 4)
    void crudDelete() {

    }
}
