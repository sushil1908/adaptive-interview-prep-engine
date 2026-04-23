package com.sushil.questionService.service;

import com.sushil.questionService.model.Question;
import com.sushil.questionService.repo.QuestionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepo  questionRepo;

    public Question addQuestion(Question question) {
        return questionRepo.save(question);
    }

    public List<Question> getAllQuestions() {
        return questionRepo.findAll();
    }

    public Question getQuestionById(Integer id) {
        return questionRepo.findById(id).get();
    }
}
