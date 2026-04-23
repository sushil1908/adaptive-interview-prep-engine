package com.sushil.questionService.repo;

import com.sushil.questionService.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepo extends JpaRepository<Question,Integer> {
}
