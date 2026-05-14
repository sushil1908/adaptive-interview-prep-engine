package com.sushil.recommendation_service.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sushil.recommendation_service.event.AttemptCompletedEvent;
import com.sushil.recommendation_service.service.RecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class RecommendationConsumer {
    @Autowired
    private RecommendationService recommendationService;

    @Autowired
    private ObjectMapper objectMapper;

    @KafkaListener(topics = "attempt-completed", groupId = "recommendation-service-group")
    public void consumeAttemptCompleted(byte[] message) {
        try {
            AttemptCompletedEvent event = objectMapper.readValue(message, AttemptCompletedEvent.class);
            System.out.println("Recommendation service received event: " + event);
            recommendationService.generateRecommendation(event);
        } catch (Exception e) {
            System.err.println("Error processing event: " + e.getMessage());
        }
    }
}
