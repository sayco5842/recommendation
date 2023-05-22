package com.example.recommendation.service;

import com.example.recommendation.domain.Recommendation;
import com.example.recommendation.dto.CreateRecommendationRequest;
import com.example.recommendation.dto.CreateRecommendationResponse;
import com.example.recommendation.dto.RecommendationDto;
import com.example.recommendation.repository.RecommendationRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@SpringBootTest
class RecommendationServiceTest {

    @Mock
    private RecommendationRepository recommendationRepository;

    @Mock
    private EncryptionService encryptionService;

    @InjectMocks
    private RecommendationService recommendationService;


    @Test
    void testAddHistory_ExistingRecommendation() {
        //given
        final CreateRecommendationRequest request = CreateRecommendationRequest.builder()
                .name("김무신사")
                .email("test@example.com")
                .gender("MALE")
                .build();

        final Recommendation existingRecommendation = Recommendation.of(
                "김무신사",
                "test@example.com",
                2L,
                LocalDateTime.now(),
                LocalDateTime.now()
        );

        //when
        when(recommendationRepository.findTopByEmail("test@example.com"))
                .thenReturn(Optional.of(existingRecommendation));
        when(encryptionService.encrypt("김무신사")).thenReturn("encryptedName");
        when(encryptionService.decrypt("encryptedName")).thenReturn("김무신사");

        //then
        CreateRecommendationResponse response = recommendationService.addHistory(RecommendationDto.from(request));
        assertEquals("test@example.com", response.getEmail());
        assertEquals(3, existingRecommendation.getRequestCount());
        assertEquals(3, response.getRequestCount());
    }

    @Test
    void testAddHistory_NewRecommendation() {
        //given
        final CreateRecommendationRequest request = CreateRecommendationRequest.builder()
                .name("김무신사")
                .email("test@example.com")
                .gender("MALE")
                .build();

        //when
        when(recommendationRepository.findTopByEmail(request.getEmail()))
                .thenReturn(Optional.empty());
        when(recommendationRepository.save(any(Recommendation.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));
        when(encryptionService.encrypt("김무신사")).thenReturn("encryptedName");
        when(encryptionService.decrypt("encryptedName")).thenReturn("김무신사");

        //then
        CreateRecommendationResponse response = recommendationService.addHistory(RecommendationDto.from(request));
        assertEquals("test@example.com", response.getEmail());
        assertEquals(1, response.getRequestCount());
    }
}