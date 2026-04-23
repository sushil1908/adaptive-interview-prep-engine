package com.sushil.questionService.dto;

import lombok.Data;

@Data
public class UpdateQuestionRequest {


    private String description;
    private String difficulty;
    private String correctAnswer;

    private String option1;
    private String option2;
    private String option3;
    private String option4;


}
