package com.sushil.recommendation_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttemptResponse {
    private Integer id;
    private Integer userId;
    private Integer score;
    private Integer totalQuestions;
    private LocalDateTime createdAt;
    private String topic;
    private String difficulty;
}