package com.fintech.chatbot_spring.Service;

import com.fintech.chatbot_spring.DTO.UserInfo;
import com.fintech.chatbot_spring.Dao.UserRepository;
import com.fintech.chatbot_spring.Domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void addUser(User user) {
        userRepository.save(user);
    }

    public List<UserInfo> getAllUsers() {
        List<UserInfo> userInfoList = new ArrayList<>();

        for(User user : userRepository.findAll())
            userInfoList.add(new UserInfo(user.getName(), user.getEmail()));

        return userInfoList;
    }
}