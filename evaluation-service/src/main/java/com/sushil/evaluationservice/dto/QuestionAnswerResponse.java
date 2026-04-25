package com.sushil.evaluationservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionAnswerResponse {

    private String questionId;
    private String correctAnswer;
}
