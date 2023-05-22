package com.example.recommendation.repository;

import com.example.recommendation.domain.Recommendation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface RecommendationRepository extends JpaRepository<Recommendation, Long> {
    Optional<Recommendation> findTopByEmail(String email);
}
