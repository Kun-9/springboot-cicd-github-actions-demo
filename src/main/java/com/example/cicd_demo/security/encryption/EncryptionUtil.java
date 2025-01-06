package com.example.cicd_demo.security.encryption;

import com.example.cicd_demo.security.dto.EncryptedData;
import com.example.cicd_demo.security.dto.EnvelopData;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class EncryptionUtil {

	private static final int GCM_TAG_LENGTH = 16;

	public static EncryptedData encrypt(EnvelopData envelopData) throws Exception {
		// AES-GCM 암호화 수행
		Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
		SecretKey secretKey = new SecretKeySpec(envelopData.getPlainDataKey(), "AES");
		GCMParameterSpec spec = new GCMParameterSpec(GCM_TAG_LENGTH * 8, envelopData.getIv());

		cipher.init(Cipher.ENCRYPT_MODE, secretKey, spec);
		byte[] encryptedData = cipher.doFinal(envelopData.getPlainText().getBytes());

		//  결과 반환
		return new EncryptedData(
				Base64.getEncoder().encodeToString(envelopData.getEncryptedDataKey()),
				Base64.getEncoder().encodeToString(envelopData.getIv()),
				Base64.getEncoder().encodeToString(encryptedData)
		);
	}
}
