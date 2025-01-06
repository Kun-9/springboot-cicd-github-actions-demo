package com.example.cicd_demo.security.dto;


// 데이터 저장 구조
public record EncryptedData(String encryptedKey, String iv, String encryptedContent) {}
