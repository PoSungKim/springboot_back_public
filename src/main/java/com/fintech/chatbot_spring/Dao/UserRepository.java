package com.fintech.chatbot_spring.Dao;

import com.fintech.chatbot_spring.DTO.UserInfo;
import com.fintech.chatbot_spring.Domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByName(String name);
    List<User> findByNameAndEmail(String name, String email);
    List<User> findByNameOrEmail(String name, String email);

    List<User> findByCreatedAtGreaterThan(LocalDateTime createdAt);
    List<User> findByCreatedAtGreaterThanEqual(LocalDateTime createdAt);
    List<User> findByCreatedAtBetween(LocalDateTime from, LocalDateTime to);
    List<User> findByCreatedAtGreaterThanEqualAndCreatedAtLessThanEqual(LocalDateTime from, LocalDateTime to);

    List<User> findByIdIsNotNull();
    List<User> findByMessagesIsNotEmpty();

    List<User> findByNameIn(List<String> names);

    List<User> findByNameStartingWith(String start);
    List<User> findByNameEndingWith(String end);
    List<User> findByNameContains(String middle);
    List<User> findByNameLike(String pattern);

    List<User> findAllByOrderByIdDescNameAsc();
    List<User> findTop10ByNameLike(String name, Sort sort);
    Page<User> findByNameLike(String name, Pageable pageable);

    User findByEmail(String email);
}