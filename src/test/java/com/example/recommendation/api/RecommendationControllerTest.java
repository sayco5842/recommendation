package com.example.recommendation.api;

import com.example.recommendation.domain.Recommendation;
import com.example.recommendation.dto.CreateRecommendationRequest;
import com.example.recommendation.dto.CreateRecommendationResponse;
import com.example.recommendation.repository.RecommendationRepository;
import com.example.recommendation.service.EncryptionService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RecommendationControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private RecommendationRepository recommendationRepository;

    @Autowired
    private EncryptionService encryptionService;

    @Test
    void testAddHistory_Success() {
        // given
        CreateRecommendationRequest request = CreateRecommendationRequest.builder()
                .name("김무신사")
                .gender("MALE")
                .email("kimmusinsa@musinsa.com")
                .build();

        // when
        ResponseEntity<CreateRecommendationResponse> response = restTemplate.postForEntity("/musinsa/recommendation", request, CreateRecommendationResponse.class);

        // then
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(request.getEmail(), response.getBody().getEmail());
        Assertions.assertEquals(request.getName(), response.getBody().getName());

        // recommendation 데이터베이스에서 조회
        Recommendation savedRecommendation = recommendationRepository.findById(response.getBody().getId()).orElse(null);
        assertNotNull(savedRecommendation);
        Assertions.assertEquals("김무신사", encryptionService.decrypt(savedRecommendation.getName()));
    }

    @Test
    void testAddHistory_BadRequest() {
        // given
        CreateRecommendationRequest request = CreateRecommendationRequest.builder()
                .name("")
                .gender("MALE2")
                .email("kimmusinsamusinsa.com")
                .build();

        // when
        ResponseEntity<CreateRecommendationResponse> response = restTemplate.postForEntity("/musinsa/recommendation", request, CreateRecommendationResponse.class);

        // then
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        System.out.println(response.getBody());
    }
}