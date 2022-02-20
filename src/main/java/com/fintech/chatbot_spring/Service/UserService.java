package com.fintech.chatbot_spring.Service;

import com.fintech.chatbot_spring.Dao.UserRepository;
import com.fintech.chatbot_spring.Domain.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
