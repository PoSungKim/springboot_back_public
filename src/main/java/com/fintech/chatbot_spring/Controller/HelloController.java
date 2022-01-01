package com.fintech.chatbot_spring.Controller;

import com.fintech.chatbot_spring.Service.RestService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Api(tags = {"테스트 용도의 Controller"})
@RestController
@RequestMapping("/hello")
@CrossOrigin(origins = {"http://localhost:81", "https://posungkim.github.io", "http://ec2-3-35-173-205.ap-northeast-2.compute.amazonaws.com"}, allowedHeaders = "*", maxAge = 3600)
public class HelloController {

    private final RestService RestService;

    @Autowired
    Environment env;

    @Value("${spring.application.name}")
    String projectName;

    @Autowired
    public HelloController(RestService RestService) {
        this.RestService = RestService;
    }

    @ApiOperation(value="WebFlux 테스트 용도 메소드")
    @GetMapping("")
    public @ResponseBody HashMap<String, Object>  hello() {
        HashMap<String, Object> item = new HashMap<String, Object>();
        item.put("data", "안녕하세요! 반갑습니다! ");
        item.put("fromWC", RestService.deleteBlockMsg());
        return item;
    }

    @ApiOperation(value="application.yml 테스트 용도 메소드")
    @GetMapping("/application.yml")
    public @ResponseBody String testApplicationYml() {
        return String.format("This is Property Environment of your project %s \n [%s] \n", projectName, env);
    }

    @ApiOperation(value="덧셈을 수행하는 메소드")
    @GetMapping("/sum/{first}")
    public int testSwaggerGet(
            @ApiParam(value = "첫 번째 값", defaultValue = "20") @PathVariable int first,
            @ApiParam(value = "두 번째 값", defaultValue = "5") @RequestParam int second) {
        return first + second;
    }

    @ApiOperation(value="돌어온 Json 파일을 그대로 리턴하는 메소드")
    @PostMapping("/echo")
    public Map<String, String> testSwaggerPost(@RequestBody Map<String, String> RequestBody) {
        return new HashMap<>(RequestBody);
    }
}