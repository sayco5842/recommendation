package com.example.recommendation.api;

import com.example.recommendation.dto.CreateRecommendationRequest;
import com.example.recommendation.dto.CreateRecommendationResponse;
import com.example.recommendation.dto.RecommendationDto;
import com.example.recommendation.service.RecommendationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/musinsa")
@RestController
public class RecommendationController {
    private final RecommendationService recommendationService;

    @PostMapping(value = "/recommendation")
    public ResponseEntity<CreateRecommendationResponse> addHistory(@Valid @RequestBody CreateRecommendationRequest request) {
        return ResponseEntity.ok(recommendationService.addHistory(RecommendationDto.from(request)));
    }
}


