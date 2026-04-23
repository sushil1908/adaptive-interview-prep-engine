package com.sushil.questionService.repo;

import com.sushil.questionService.model.Question;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepo extends JpaRepository<Question,Integer> {
    List<Question> getQuestionsByTopic(String topic);

    List<Question> getQuestionsByDifficulty(String difficulty);

    @Query("SELECT q FROM Question q WHERE q.topic = :topic AND q.difficulty = :difficulty")
    List<Question> filter(@Param("topic") String topic, @Param("difficulty") String difficulty);

    @Query(value = "SELECT * FROM question ORDER BY RANDOM()", nativeQuery = true)
    List<Question> findRandomQuestions(Pageable pageable);
}
