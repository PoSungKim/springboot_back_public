package com.fintech.chatbot_spring.Domain;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;

public class UserTest {

    @Test
    void constructorTest() {
        // @NoArgsConstructor
        User user = new User();
        user.setName("NoArgsConstructor");
        user.setEmail("test@test.com");
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
        System.out.println(user);

        // @AllArgsConstructor
        User user2 = new User("AllArgsConstructor", "test@test.com", LocalDateTime.now(), LocalDateTime.now());
        System.out.println(user2);

        // @RequiredArgsConstructor
        User user3 = new User("RequiredArgsConstructor", "test@test.com");
        System.out.println(user3);

        // @Builder
        User user4 = User.builder()
                .name("Builder")
                .email("test@test.com")
                .build();
        System.out.println(user4);
    }


}
