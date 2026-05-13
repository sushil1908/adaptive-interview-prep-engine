package com.sushil.evaluationservice.repo;

import com.sushil.evaluationservice.model.Attempt;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AttemptRepo extends JpaRepository<Attempt,Integer> {
    List<Attempt> findByUserId(Integer userId);
}
