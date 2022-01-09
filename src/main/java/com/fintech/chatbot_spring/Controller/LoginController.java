package com.fintech.chatbot_spring.Controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@Api(tags = {"로그인 Controller"})
@RestController
@RequestMapping(value = "/logintest")
public class LoginController {

    @ApiOperation(value="Base64 Encoding 전통 방식의 GET 로그인")
    @GetMapping("")
    public String login() {
        return "Success";
    }

    @ApiOperation(value="Base64 Encoding 전통 방식의 POST 로그인")
    @PostMapping("")
    public String login(@RequestBody String request) { return "Success : " + request; }

    @ApiOperation("자신의 Authenticity 정보 조회")
    @GetMapping("/auth")
    public Authentication getMyAuthInfo() {
        return SecurityContextHolder.getContext().getAuthentication();
    }
}