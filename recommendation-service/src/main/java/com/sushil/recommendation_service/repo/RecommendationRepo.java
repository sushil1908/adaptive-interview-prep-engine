package com.sushil.recommendation_service.repo;


import com.sushil.recommendation_service.model.Recommendation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RecommendationRepo extends JpaRepository<Recommendation, Integer> {

    Optional<Recommendation> findTopByUserIdOrderByCreatedAtDesc(Integer userId);
}
