package com.sushil.evaluationservice.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AttemptCompletedEvent {
    private Integer userId;
    private Integer score;
    private Integer totalQuestions;
    private List<Integer> questionIds;
    private List<Boolean> correctness;
}
