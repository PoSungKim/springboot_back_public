package com.fintech.chatbot_spring.Dao;

import com.fintech.chatbot_spring.Domain.User;
import com.fintech.chatbot_spring.Domain.UserMeta;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.time.LocalDateTime;

@SpringBootTest
class UserMetaRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserMetaRepository userMetaRepository;

    private final static Logger logger = LoggerFactory.getLogger(UserMetaRepositoryTest.class);

    @Test
    public void OneToOneTest() {

        User user = new User();
        user.setName("pskim");
        user.setEmail("pskim@test.com");
        userRepository.save(user);

        UserMeta userMeta = new UserMeta();
        userMeta.setAccessCount(1);
        userMeta.setLastAccessDate(LocalDateTime.now());
        userMeta.setUser(user);

        userMetaRepository.save(userMeta);

        userMeta = userMetaRepository.findById(1L).orElseThrow(RuntimeException::new);
        logger.info("userMetaRepository" + userMeta);
        logger.info("user of userMetaRepository" + userMeta.getUser());

        System.out.println();

        user = userRepository.findByName("pskim").get(0);
        logger.info("userRepository" + user);
        logger.info("userMeta of userRepository" + user.getUserMeta());
    }

}