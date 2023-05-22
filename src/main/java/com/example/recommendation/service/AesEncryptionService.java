package com.example.recommendation.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Service
public class AesEncryptionService implements EncryptionService {
    @Value("${spring.encryption.secretkey}")
    private String SECRET_KEY;

    @Override
    public String encrypt(@NonNull String value) {
        try {
            Cipher cipher = Cipher.getInstance("AES");
            SecretKey secretKey = new SecretKeySpec(SECRET_KEY.getBytes(StandardCharsets.UTF_8), "AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] encryptedBytes = cipher.doFinal(value.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(encryptedBytes);
        } catch (Exception e) {
            // 예외 처리 로직 작성
            e.printStackTrace();
            // 예외 발생시 대체 처리할 코드
            return null;
        }
    }

    @Override
    public String decrypt(@NonNull String encryptedValue) {
        try {
            Cipher cipher = Cipher.getInstance("AES");
            SecretKey secretKey = new SecretKeySpec(SECRET_KEY.getBytes(StandardCharsets.UTF_8), "AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedValue));
            return new String(decryptedBytes, StandardCharsets.UTF_8);
        } catch (Exception e) {
            // 예외 처리 로직 작성
            e.printStackTrace();
            // 예외 발생시 대체 처리할 코드
            return null;
        }
    }
}
