package com.fintech.chatbot_spring.Service;

import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.HashMap;

@Service
public class RestService {

    private WebClient WC;

    public RestService(WebClient.Builder webClientBuilder) {
        WC = webClientBuilder.baseUrl("http://localhost:8081").build();
    }

    public HashMap getBlockMsg() {
        return WC.method(HttpMethod.GET)
                .uri("/user/1")
                .retrieve()
                .bodyToMono(HashMap.class)
                .block();
    }

    public HashMap postBlockMsg() {
        return WC.method(HttpMethod.POST)
                .uri("/user")
                .bodyValue(new HashMap(){{
                    put("name", "brian");
                    put("id", 1);
                    put("email", "test@test.com");
                }})
                .retrieve()
                .bodyToMono(HashMap.class)
                .block();
    }

    public HashMap putBlockMsg() {
        return WC.method(HttpMethod.PUT)
                .uri("/user")
                .bodyValue(new HashMap(){{
                    put("name", "putputputput");
                    put("id", 1);
                    put("email", "test@test.com");
                }})
                .retrieve()
                .bodyToMono(HashMap.class)
                .block();
    }

    public HashMap deleteBlockMsg() {
        return WC.method(HttpMethod.DELETE)
                .uri("/user/100")
                .retrieve()
                .bodyToMono(HashMap.class)
                .block();
    }
}