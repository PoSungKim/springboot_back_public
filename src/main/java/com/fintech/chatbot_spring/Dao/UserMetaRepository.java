package com.fintech.chatbot_spring.Dao;

import com.fintech.chatbot_spring.Domain.UserMeta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserMetaRepository extends JpaRepository<UserMeta, Long> {

}
