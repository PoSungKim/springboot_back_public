package com.fintech.chatbot_spring.Controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@CrossOrigin
@RestController
@RequestMapping("/hello")
public class HelloController {
    
    @GetMapping("/")
    public @ResponseBody HashMap<String, Object>  hello() {
        HashMap<String, Object> item = new HashMap<String, Object>();
        item.put("data", "안녕하세요! 반갑습니다! ");
        return item;
    }
}