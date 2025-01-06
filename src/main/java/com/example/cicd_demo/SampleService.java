package com.example.cicd_demo;

import com.example.cicd_demo.security.secrets_manager.AwsSecretUtil;
import com.example.cicd_demo.security.kms.KMSUtil;
import com.example.cicd_demo.security.dto.EncryptedData;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SampleService {
	private final KMSUtil kmsUtil;
	private final AwsSecretUtil awsSecretUtil;

	public EncryptedData encryptText(String plainText) throws Exception {
		return kmsUtil.encrypt(plainText);
	}

	public JsonNode getSecretObject(String secretName) {
		return awsSecretUtil.getSecretObject(secretName);
	}

	public String getSecretValue(String secretName, String secretId) {
		return awsSecretUtil.getSecretValue(secretName, secretId);
	}
}
