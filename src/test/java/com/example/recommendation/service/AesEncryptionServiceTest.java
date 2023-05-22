package com.example.recommendation.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest
class AesEncryptionServiceTest {

    @Autowired
    private AesEncryptionService encryptionService;

    @Test
    void testEncryptionAndDecryption() {
        // given
        String originalValue = "김무신사";

        // when
        String encryptedValue = encryptionService.encrypt(originalValue);
        String decryptedValue = encryptionService.decrypt(encryptedValue);

        // then
        assertNotEquals(originalValue, encryptedValue);
        assertEquals(originalValue, decryptedValue);
    }

}