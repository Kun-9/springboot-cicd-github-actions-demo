package com.example.cicd_demo;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class Check {
	private final Environment environment;

	@PostConstruct
	public void init() {
		log.info("Check Environment : {}", environment.getActiveProfiles());
		log.info("Check Environment : {}", environment.getProperty("spring.profiles.active"));
		log.info("Check Environment : {}", environment.getProperty("spring.datasource.username"));
	}

}
