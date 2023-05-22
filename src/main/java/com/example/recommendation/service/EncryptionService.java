package com.example.recommendation.service;

public interface EncryptionService {
    String encrypt(String value);

    String decrypt(String encryptedValue);
}
