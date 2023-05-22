package com.example.recommendation.dto;

import com.example.recommendation.domain.Recommendation;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import org.springframework.lang.NonNull;

import java.time.LocalDateTime;

@Getter
public class CreateRecommendationResponse {
    private final Long id;
    private final String name;
    private final String email;
    private final Long requestCount;
    private final LocalDateTime createdDateTime;
    private final LocalDateTime updatedDateTime;

    private CreateRecommendationResponse(Long id,
                                         String name,
                                         String email,
                                         Long requestCount,
                                         LocalDateTime createdDateTime,
                                         LocalDateTime updatedDateTime
    ) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.requestCount = requestCount;
        this.createdDateTime = createdDateTime;
        this.updatedDateTime = updatedDateTime;
    }

    public static CreateRecommendationResponse of(@NonNull Recommendation recommendation, @NonNull String decryptedName) {
        return new CreateRecommendationResponse(
                recommendation.getId(),
                decryptedName,
                recommendation.getEmail(),
                recommendation.getRequestCount(),
                recommendation.getCreatedDateTime(),
                recommendation.getUpdatedDateTime()
        );
    }

}
