package com.fintech.chatbot_spring.Dao;

import com.fintech.chatbot_spring.Domain.UserAndUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAndUserRepository extends JpaRepository<UserAndUser, Long> {
}
