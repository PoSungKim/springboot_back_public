package com.fintech.chatbot_spring.Domain;

import com.fintech.chatbot_spring.Dao.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserAndUserTest {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    UserRepository userRepository;

    @Test
    @Transactional
    public void createEntity() {

        UserAndUser userAndUser = new UserAndUser();
        userAndUser.setFromUser(userRepository.findById(1L).orElseThrow(RuntimeException::new));
        userAndUser.setToUser(userRepository.findById(2L).orElseThrow(RuntimeException::new));

        System.out.println(userAndUser);
    }
}