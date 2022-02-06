package com.fintech.chatbot_spring.Domain;

import com.fintech.chatbot_spring.Dao.UserMetaRepository;
import com.fintech.chatbot_spring.Dao.UserRepository;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.time.LocalDateTime;

@SpringBootTest
class UserMetaTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserMetaRepository userMetaRepository;

    private final static Logger logger = LoggerFactory.getLogger(UserMetaTest.class);

    @Test
    public void OneToOneTest() {

        User user = userRepository.findByName("Ryan").get(0);

        UserMeta userMeta = UserMeta
                .builder()
                    .id(1L)
                .accessCount(1)
                .lastAccessDate(LocalDateTime.now())
                .build();

        user.setUserMeta(userMeta);
        userMeta.setUser(user);

        userMetaRepository.save(userMeta);
        userRepository.save(user);

        logger.info("userMetaRepository" + userMeta);
        logger.info("user of userMetaRepository" + userMeta.getUser());

        System.out.println();

        logger.info("userRepository" + user);
        logger.info("userMeta of userRepository" + user.getUserMeta());
    }

}