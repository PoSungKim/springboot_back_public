package com.fintech.chatbot_spring.Controller;

import com.fintech.chatbot_spring.Service.RestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/hello")
@CrossOrigin(origins = {"http://localhost:81", "https://posungkim.github.io", "http://ec2-3-35-173-205.ap-northeast-2.compute.amazonaws.com"}, allowedHeaders = "*", maxAge = 3600)
public class HelloController {

    private final RestService RestService;

    @Autowired
    public HelloController(RestService RestService) {
        this.RestService = RestService;
    }

    @GetMapping("")
    public @ResponseBody HashMap<String, Object>  hello() {
        HashMap<String, Object> item = new HashMap<String, Object>();
        item.put("data", "안녕하세요! 반갑습니다! ");
        item.put("fromWC", RestService.deleteBlockMsg());
        return item;
    }
}