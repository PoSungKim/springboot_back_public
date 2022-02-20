package com.fintech.chatbot_spring.Controller;

import com.fintech.chatbot_spring.Service.UserService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = {"http://localhost:81", "https://posungkim.github.io", "http://ec2-3-35-173-205.ap-northeast-2.compute.amazonaws.com"}, allowedHeaders = "*", maxAge = 3600)
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public Map<Object, Object> getAllUsers() {
        Map<Object, Object> payload = new HashMap<>();
        payload.put("data", userService.getAllUsers());

        return payload;
    }
}