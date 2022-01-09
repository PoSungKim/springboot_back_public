package com.fintech.chatbot_spring.Config;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Base64;

@SpringBootTest(webEnvironment =  SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BasicAuthenticationTest {

    private static final Logger logger = LoggerFactory.getLogger(BasicAuthenticationTest.class);

    @LocalServerPort
    int port;

    RestTemplate restTemplate = new RestTemplate();

    private URI getLoginUrl() {
        return UriComponentsBuilder
                .fromUriString("http://localhost:" + port)
                .path("/logintest")
                .encode()
                .build()
                .toUri();
    }

    @DisplayName("1. 실패 케이스 - Header에 Base64 Encoding 값 미포함")
    @Test
    void failCase() {
        String response = restTemplate.getForObject(getLoginUrl(), String.class);
        logger.warn(response);
    }

    @DisplayName("2. 성공 케이 스 - Header에 Base64 Encoding 값 포함")
    @Test
    void successCase() {

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.AUTHORIZATION, "Basic " + Base64.getEncoder().encodeToString(
                "user:user".getBytes()
        ));

        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(getLoginUrl(), HttpMethod.GET, entity, String.class);
        logger.warn(response.getBody());
    }

}