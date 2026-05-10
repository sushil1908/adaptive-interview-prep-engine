package com.sushil.questionService.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionResponse {

    private Integer id;
    private String description;
    private String topic;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private String difficulty;

}
