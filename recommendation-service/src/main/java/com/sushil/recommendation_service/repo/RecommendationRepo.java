package com.sushil.recommendation_service.repo;


import com.sushil.recommendation_service.model.Recommendation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RecommendationRepo extends JpaRepository<Recommendation, Integer> {

    Optional<Recommendation> findTopByUserIdOrderByCreatedAtDesc(Integer userId);
}
