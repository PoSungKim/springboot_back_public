package com.fintech.chatbot_spring.Dao;

import com.fintech.chatbot_spring.Domain.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
}
