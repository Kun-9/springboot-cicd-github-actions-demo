package com.example.cicd_demo.security.secrets_manager;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.services.secretsmanager.SecretsManagerClient;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueRequest;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueResponse;
import software.amazon.awssdk.services.secretsmanager.model.SecretsManagerException;

@RequiredArgsConstructor
@Component
@Slf4j
public class AwsSecretUtil {
	private final SecretsManagerClient secretsManager;

	public JsonNode getSecretObject(String secretsName) {
		try {
			GetSecretValueRequest request = GetSecretValueRequest.builder()
					.secretId(secretsName)
					.build();

			GetSecretValueResponse response = secretsManager.getSecretValue(request);

			ObjectMapper objectMapper = new ObjectMapper();
			return objectMapper.readTree(response.secretString());

		} catch (SecretsManagerException | JsonProcessingException e) {
			log.error("Failed to get secretName", e);
			throw new RuntimeException("Secret Name 조회 실패");
		}
	}

	public String getSecretValue(String secretName, String secretId) {
		try {
			JsonNode secretObject = getSecretObject(secretName);
			return secretObject.get(secretId).asText();
		} catch (SecretsManagerException e) {
			log.error("Failed to get SecretId", e);
			throw new RuntimeException("Secret Id 조회 실패");
		}
	}
}
