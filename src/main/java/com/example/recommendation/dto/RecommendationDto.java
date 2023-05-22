package com.example.recommendation.dto;

import lombok.Getter;
import org.springframework.lang.NonNull;

import java.time.LocalDateTime;

@Getter
public class RecommendationDto {
    private final String name;
    private final String email;
    private final Long requestCount;
    private final LocalDateTime createdDateTime;
    private final LocalDateTime updatedDateTime;

    private RecommendationDto(String name, String email, Long requestCount, LocalDateTime createdDateTime, LocalDateTime updatedDateTime) {
        this.name = name;
        this.email = email;
        this.requestCount = requestCount;
        this.createdDateTime = createdDateTime;
        this.updatedDateTime = updatedDateTime;
    }

    public static RecommendationDto from(@NonNull CreateRecommendationRequest recommendationRequest) {
        return new RecommendationDto(
                recommendationRequest.getName(),
                recommendationRequest.getEmail(),
                0L,
                LocalDateTime.now(),
                LocalDateTime.now()
        );
    }
}
