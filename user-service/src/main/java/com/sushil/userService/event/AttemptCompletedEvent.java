package com.sushil.userService.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttemptCompletedEvent {
    private Integer userId;
    private Integer score;
    private Integer totalQuestions;
}
