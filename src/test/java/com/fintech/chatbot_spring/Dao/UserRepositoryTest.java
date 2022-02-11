package com.fintech.chatbot_spring.Dao;

import com.fintech.chatbot_spring.Domain.Message;
import com.fintech.chatbot_spring.Domain.User;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.endsWith;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserRepositoryTest {
    private static final Logger logger = LoggerFactory.getLogger(MessageRepositoryTest.class);

    @Autowired
    UserRepository userRepository;

    @Test
    @Order(1)
    void crudCreate() {
        userRepository.save(User.builder()
                .name("Josh")
                .email("Josh@test.com")
//                .createdAt(LocalDateTime.now())
//                .updatedAt(LocalDateTime.now())
                .build());

        User user1 = User.builder()
                .name("Brian")
                .email("Brian@test.com")
//                .createdAt(LocalDateTime.now())
//                .updatedAt(LocalDateTime.now())
                .build();

        User user2 = User.builder()
                .name("Tommy")
                .email("Tommy@test.com")
//                .createdAt(LocalDateTime.now())
//                .updatedAt(LocalDateTime.now())
                .build();

        userRepository.saveAll(Arrays.asList(user1, user2));

        userRepository.flush();
    }

    @Test
    @Order(2)
    void crudRead() {
        logger.trace(userRepository.findAll().toString());
        logger.info(userRepository.findById(1L).toString());

        // User-defined Query Method
        logger.debug(userRepository.findByNameAndEmail("Brian", "Brian@test.com").toString());
        logger.debug(userRepository.findByNameOrEmail("Brian", "@test.com").toString());

        logger.debug(userRepository.findByCreatedAtGreaterThan(LocalDateTime.now().minusDays(1L)).toString());
        logger.debug(userRepository.findByCreatedAtGreaterThanEqual(LocalDateTime.now().minusDays(1L)).toString());

        logger.debug(userRepository.findByCreatedAtBetween(LocalDateTime.now().minusDays(1L), LocalDateTime.now().plusDays(1L)).toString());
        logger.debug(userRepository.findByCreatedAtGreaterThanEqualAndCreatedAtLessThanEqual(LocalDateTime.now().minusDays(1L), LocalDateTime.now().plusDays(1L)).toString());

        logger.debug(userRepository.findByIdIsNotNull().toString());
        logger.debug(userRepository.findByMessagesIsNotEmpty().toString());

        logger.info(userRepository.findByNameIn(Arrays.asList("Brian", "Josh")).toString());

        logger.info(userRepository.findByNameStartingWith("Bri").toString());
        logger.info(userRepository.findByNameEndingWith("an").toString());
        logger.info(userRepository.findByNameContains("ria").toString());
        logger.info(userRepository.findByNameLike("%ria%").toString());
    }

    @Test
    @Order(3)
    void crudMetaRead() {
        logger.debug(Integer.toString((int)userRepository.count()));
        logger.warn(String.valueOf(userRepository.existsById(1L)));
    }

    @Test
    @Order(4)
    void crudPaginationRead() {

        // QueryMethod를 통한 Pagination (1)
        Page<User> users = userRepository.findAll(PageRequest.of(0, 5));
        // toString
        logger.info("users : " + users);
        // 전체 elements (user) 개수
        logger.info("total elements : " + users.getTotalElements());
        // 전체 page 개수
        logger.info("total pages : " + users.getTotalPages());
        // 해당 page의 elements 개수
        logger.info("number of elements : " + users.getNumberOfElements());
        // SORT 상태
        logger.info("sort : " + users.getSort());
        // slice의 Pageable 크기
        logger.info("size : " + users.getSize());
        // elements 조회
        logger.info("content : " + users.getContent());

        // QueryMethod를 통한 Pagination (2)
        logger.info("findAllByOrderByIdDescNameAsc : " + userRepository.findAllByOrderByIdDescNameAsc());
        logger.info("findTop10ByNameLike : " + userRepository.findTop10ByNameLike("%o%", Sort.by(Sort.Order.desc("id"), Sort.Order.asc("email"))));
        logger.info("findByNameLike : " + userRepository.findByNameLike("%o%", PageRequest.of(1, 1, Sort.by(Sort.Order.desc("email")))).getContent());
    }

    @Test
    @Order(5)
    void crudExampleRead() {
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnorePaths("name", "updatedAt", "createdAt")
                .withMatcher("email", endsWith());

        Example<User> example = Example.of(
                User.builder()
                .name("users")
                .email("users@test.com")
//                .createdAt(LocalDateTime.now())
//                .updatedAt(LocalDateTime.now())
                .build()
        , matcher);

        logger.info(userRepository.findAll(example).toString());
    }

    @Test
    @Order(6)
    void crudUpdate() {
        List<User> users = userRepository.findByName("Brian");
        logger.info(users.toString());

        users.get(0).setEmail("BRIAN@test.com");
        userRepository.saveAll(users);

        users = userRepository.findByName("Brian");
        logger.info(users.toString());
    }

    @Test
    @Order(7)
    void crudDelete() {
        userRepository.deleteById(1L);
        userRepository.deleteAllByIdInBatch(Arrays.asList(2L, 3L));
        userRepository.deleteAllInBatch();
    }
}