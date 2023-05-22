package com.example.recommendation.service;

import com.example.recommendation.domain.Recommendation;
import com.example.recommendation.dto.CreateRecommendationResponse;
import com.example.recommendation.dto.RecommendationDto;
import com.example.recommendation.repository.RecommendationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RecommendationService {
    private final RecommendationRepository recommendationRepository;
    private final EncryptionService encryptionService;

    @Transactional
    public CreateRecommendationResponse addHistory(@NonNull RecommendationDto recommendationDto) {
        final Recommendation recommendation = recommendationRepository.findTopByEmail(recommendationDto.getEmail())
                .orElseGet(() -> saveEntity(recommendationDto));
        recommendation.incrementCount();

        return CreateRecommendationResponse.of(recommendation, encryptionService.decrypt(recommendation.getName()));
    }

    private Recommendation saveEntity(RecommendationDto recommendationDto) {
        return recommendationRepository.save(
                Recommendation.of(
                        encryptionService.encrypt(recommendationDto.getName()),
                        recommendationDto.getEmail(),
                        recommendationDto.getRequestCount(),
                        recommendationDto.getCreatedDateTime(),
                        recommendationDto.getUpdatedDateTime()
                )
        );
    }
}
