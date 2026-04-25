package com.sushil.evaluationservice.dto;


import lombok.Data;

import java.util.List;

@Data
public class QuestionAnswerRequest {
    List<Integer> questionIds;
}
