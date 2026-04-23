package com.sushil.questionService.service;

import com.sushil.questionService.model.Question;
import com.sushil.questionService.repo.QuestionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
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

    public List<Question> getQuestionsByTopic(String topic) {
        return questionRepo.getQuestionsByTopic(topic);
    }

    public List<Question> getQuestionsByDifficulty(String difficulty) {
        return questionRepo.getQuestionsByDifficulty(difficulty);
    }

    public List<Question> filter(String topic, String difficulty) {
        return questionRepo.filter(topic,difficulty);
    }

    public List<Question> getRandomQuestions(String count) {
        return questionRepo.findRandomQuestions(PageRequest.of(0,Integer.parseInt(count)));
    }
}
