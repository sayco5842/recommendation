package com.example.recommendation.repository;

import com.example.recommendation.domain.Recommendation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class RecommendationRepositoryTest {
    @Autowired
    RecommendationRepository recommendationRepository;

    @Test
    void saveRecommendation() {
        //given
        final Recommendation recommendation = Recommendation.of(
                "김무신사",
                "kimmusinsa@musinsa.com",
                0L,
                LocalDateTime.now(),
                LocalDateTime.now()
        );

        //when
        final Recommendation saveRecommendation = recommendationRepository.save(recommendation);

        //then
        assertEquals(recommendation.getId(), saveRecommendation.getId());
        assertEquals(recommendation.getName(), saveRecommendation.getName());
        assertEquals(recommendation.getEmail(), saveRecommendation.getEmail());
        assertEquals(recommendation.getRequestCount(), saveRecommendation.getRequestCount());
        assertEquals(recommendation.getCreatedDateTime(), saveRecommendation.getCreatedDateTime());
        assertEquals(recommendation.getUpdatedDateTime(), saveRecommendation.getUpdatedDateTime());

    }
}