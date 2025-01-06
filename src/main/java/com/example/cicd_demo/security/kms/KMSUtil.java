package com.example.cicd_demo.security.kms;

import com.example.cicd_demo.security.dto.EncryptedData;
import com.example.cicd_demo.security.dto.EnvelopData;
import com.example.cicd_demo.security.encryption.EncryptionUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.services.kms.KmsClient;
import software.amazon.awssdk.services.kms.model.*;

import java.security.SecureRandom;

@Component
@Slf4j
public class KMSUtil {

	private static final int GCM_IV_LENGTH = 12;

	private final String keyArn = "arn:aws:kms:ap-northeast-2:867344463619:key/958cee2f-5f6e-4ebe-b107-c828e31f9df3";
	private final KmsClient kmsClient;

	public KMSUtil(KmsClient kmsClient) {
		this.kmsClient = kmsClient;
	}

	// 암호화 메소드
	public EncryptedData encrypt(String plainText) throws Exception {

		GenerateDataKeyRequest dataKeyRequest = GenerateDataKeyRequest.builder()
				.keyId(keyArn)
				.keySpec(DataKeySpec.AES_256)
				.build();

		GenerateDataKeyResponse dataKeyResponse = kmsClient.generateDataKey(dataKeyRequest);

		byte[] plainDataKey = dataKeyResponse.plaintext().asByteArray();
		byte[] encryptedDataKey = dataKeyResponse.ciphertextBlob().asByteArray();
		byte[] iv = new byte[GCM_IV_LENGTH];

		SecureRandom random = new SecureRandom();
		random.nextBytes(iv);

		EnvelopData envelopData = new EnvelopData(plainText, plainDataKey, iv, encryptedDataKey);

		// AES-GCM 암호화 수행
		return EncryptionUtil.encrypt(envelopData);
	}
}
