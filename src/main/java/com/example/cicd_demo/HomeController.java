package com.example.cicd_demo;

import com.example.cicd_demo.security.dto.EncryptedData;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
public class HomeController {
    private final Environment environment;
    private final SampleService sampleService;

    @GetMapping("/home")
    public String home() {
        StringBuilder sb = new StringBuilder();

        sb.append("Current Active : ").append(environment.getProperty("spring.profiles.active")).append("\n");
        sb.append("Current ENV : ").append(environment.getProperty("temp.test.value")).append("\n");

        log.info("Home 접속");
        log.debug("디버그 메시지");
        log.info("정보 메시지");
        log.warn("경고 메시지");
        log.error("에러 메시지");

        return sb.toString();
    }

    @GetMapping("/encrypt/{text}")
    public EncryptedData encrypt(@PathVariable String text) throws Exception {
        return sampleService.encryptText(text);
    }

    @GetMapping("/secret/object")
    public JsonNode secretObject(@RequestParam String secretName) {
        return sampleService.getSecretObject(secretName);
    }

    @GetMapping("/secret/value")
    public String secretValue(
            @RequestParam String secretName,
            @RequestParam String secretId
    ) {
        return sampleService.getSecretValue(secretName, secretId);
    }
}