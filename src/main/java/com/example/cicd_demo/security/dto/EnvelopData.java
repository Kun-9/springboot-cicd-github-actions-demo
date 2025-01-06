package com.example.cicd_demo.security.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class EnvelopData {
	private final String plainText;
	private final byte[] plainDataKey;
	private final byte[] iv;
	private final byte[] encryptedDataKey;
}
