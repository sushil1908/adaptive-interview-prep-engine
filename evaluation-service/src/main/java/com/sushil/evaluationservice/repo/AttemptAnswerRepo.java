package com.sushil.evaluationservice.repo;

import com.sushil.evaluationservice.model.AttemptAnswer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttemptAnswerRepo extends JpaRepository<AttemptAnswer,Integer> {
}
