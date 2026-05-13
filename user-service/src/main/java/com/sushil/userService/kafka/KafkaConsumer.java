package com.sushil.userService.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sushil.userService.event.AttemptCompletedEvent;
import com.sushil.userService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    @Autowired
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    @KafkaListener(topics = "attempt-completed", groupId = "user-service-group")
    public void consumeAttemptCompleted(byte[] message) {
        try {
            AttemptCompletedEvent event = objectMapper.readValue(message, AttemptCompletedEvent.class);
            System.out.println("Received Kafka event: " + event);
            userService.updateProgress(event.getUserId(), event.getScore(), event.getTotalQuestions());
        } catch (Exception e) {
            System.err.println("Error processing Kafka event: " + e.getMessage());
        }
    }
}