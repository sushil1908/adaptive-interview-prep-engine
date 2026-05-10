package com.sushil.evaluationservice.repo;

import com.sushil.evaluationservice.model.Attempt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttemptRepo extends JpaRepository<Attempt,Integer> {
}
