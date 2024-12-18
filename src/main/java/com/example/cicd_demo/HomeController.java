package com.example.cicd_demo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
public class HomeController {
    private final Environment environment;

    @GetMapping("/")
    public String home() {
        StringBuilder sb = new StringBuilder();

        sb.append("Current Active : ").append(environment.getProperty("spring.profiles.active")).append("\n");
        sb.append("Current ENV : ").append(environment.getProperty("temp.test.value")).append("\n");

        log.debug("디버그 메시지");
        log.info("정보 메시지");
        log.warn("경고 메시지");
        log.error("에러 메시지");

        return sb.toString();
    }
}