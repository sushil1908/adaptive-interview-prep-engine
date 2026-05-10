package com.sushil.evaluationservice.dto;

import lombok.Data;

import java.util.List;

@Data
public class SubmitRequest {

    private Integer userId;
    private List<AnswerDTO> answers;
}
