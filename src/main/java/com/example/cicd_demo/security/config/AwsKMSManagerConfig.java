package com.example.cicd_demo.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.kms.KmsClient;

@Configuration
public class AwsKMSManagerConfig {

	@Bean
	public KmsClient kmsClient() {

		return KmsClient.builder()
				.region(Region.AP_NORTHEAST_2)
				.build();
	}
}
