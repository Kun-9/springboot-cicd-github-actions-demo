package com.example.cicd_demo;


import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RestController
public class HomeController {


    private final Environment environment;

    @GetMapping("/")
    public String home() {

        StringBuilder sb = new StringBuilder();

        sb.append("Current Active : ").append(environment.getProperty("spring.profiles.active")).append("\n");
        sb.append("Current ENV : ").append(environment.getProperty("temp.test.value")).append("\n");

        return sb.toString();
    }
}



