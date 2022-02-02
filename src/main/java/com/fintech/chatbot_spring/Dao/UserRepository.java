package com.fintech.chatbot_spring.Dao;

import com.fintech.chatbot_spring.Domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}