package com.sushil.evaluationservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Attempt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer userId;
    private Integer score;
    private Integer totalQuestions;
    private LocalDateTime createdAt;

    @ElementCollection
    private List<Integer> questionIds;
    @ElementCollection
    private List<Boolean> correctness;
}
