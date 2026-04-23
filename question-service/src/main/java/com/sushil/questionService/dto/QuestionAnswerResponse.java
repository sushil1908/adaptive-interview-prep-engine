package com.sushil.questionService.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionAnswerResponse {

    private Integer id;
    private String correctAnswer;
}
