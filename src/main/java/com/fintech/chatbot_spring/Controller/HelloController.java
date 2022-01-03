package com.fintech.chatbot_spring.Controller;

import com.fintech.chatbot_spring.Service.RestService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.*;

@Api(tags = {"테스트 용도의 Controller"})
@RestController
@RequestMapping("/hello")
@CrossOrigin(origins = {"http://localhost:81", "https://posungkim.github.io", "http://ec2-3-35-173-205.ap-northeast-2.compute.amazonaws.com"}, allowedHeaders = "*", maxAge = 3600)
public class HelloController {

    private static final Logger logger = LoggerFactory.getLogger(HelloController.class);

    private final RestService restService;

    @Autowired
    Environment env;

    @Autowired
    RestTemplate restTemplate;

    @Value("${spring.application.name}")
    String projectName;

    @Autowired
    public HelloController(RestService RestService, RestTemplate restTemplate) {
        this.restService = RestService;
        this.restTemplate = restTemplate;
    }

    @ApiOperation(value="WebFlux 테스트 용도 메소드")
    @GetMapping("")
    public @ResponseBody HashMap<String, Object>  hello() {
        HashMap<String, Object> item = new HashMap<String, Object>();
        item.put("data", "안녕하세요! 반갑습니다! ");
        item.put("fromWC", restService.deleteBlockMsg());
        return item;
    }

    @ApiOperation(value="application.yml 테스트 용도 메소드")
    @GetMapping("/application.yml")
    public @ResponseBody String applicationYml() {
        return String.format("This is Property Environment of your project %s \n [%s] \n", projectName, env);
    }

    @ApiOperation(value="덧셈을 수행하는 메소드")
    @GetMapping("/sum/{first}")
    public int sum(
            @ApiParam(value = "첫 번째 값", defaultValue = "20") @PathVariable int first,
            @ApiParam(value = "두 번째 값", defaultValue = "5") @RequestParam int second) {
        return first + second;
    }

    @ApiOperation(value="돌어온 Json 파일을 그대로 리턴하는 메소드")
    @PostMapping("/echo")
    public Map<String, String> echo(@RequestBody Map<String, String> RequestBody) {
        return new HashMap<>(RequestBody);
    }

    @ApiOperation(value="@Configuration + @Bean Test")
    @GetMapping("/restTemplateConfig")
    public String getRestTemplateConfig() {
        URI uri = UriComponentsBuilder
                .fromUriString("http://google.com")
                .encode()
                .build()
                .toUri();

        ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);

        logger.warn(result.getHeaders().toString());

        return result.getHeaders().toString();
    }
}