package com.sushil.recommendation_service.controller;

import com.sushil.recommendation_service.model.Recommendation;
import com.sushil.recommendation_service.service.RecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("recommendation")
public class RecommendationController {
    @Autowired
    private RecommendationService recommendationService;

    @GetMapping("/{userId}")
    public ResponseEntity<Recommendation> getRecommendation(@PathVariable Integer userId) {
        Recommendation recommendation = recommendationService.getLatestRecommendation(userId);
        if (recommendation == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(recommendation);
    }
}
