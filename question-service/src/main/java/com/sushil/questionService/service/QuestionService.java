package com.sushil.questionService.service;

import com.sushil.questionService.dto.QuestionAnswerResponse;
import com.sushil.questionService.dto.QuestionResponse;
import com.sushil.questionService.dto.UpdateQuestionRequest;
import com.sushil.questionService.exception.QuestionNotFoundException;
import com.sushil.questionService.model.Question;
import com.sushil.questionService.repo.QuestionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepo  questionRepo;

    private QuestionResponse toResponse(Question question){
        return new QuestionResponse(
                question.getId(),
                question.getDescription(),
                question.getOption1(),
                question.getOption2(),
                question.getOption3(),
                question.getOption4(),
                question.getDifficulty(),
                question.getTopic()
        );
    }

    public Question addQuestion(Question question) {
        return questionRepo.save(question);
    }

    public List<QuestionResponse> getAllQuestions() {
        return questionRepo.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public QuestionResponse getQuestionById(Integer id) {
        Question q= questionRepo.findById(id).orElseThrow(()-> new QuestionNotFoundException("Question not found with id: " + id));
        return toResponse(q);
    }

    public List<QuestionResponse> getQuestionsByTopic(String topic) {
        return questionRepo.getQuestionsByTopic(topic)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public List<QuestionResponse> getQuestionsByDifficulty(String difficulty) {
        return questionRepo.getQuestionsByDifficulty(difficulty)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public List<QuestionResponse> filter(String topic, String difficulty) {
        return questionRepo.filter(topic,difficulty)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public List<QuestionResponse> getRandomQuestions(String count) {
        return questionRepo.findRandomQuestions(PageRequest.of(0,Integer.parseInt(count)))
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public void deleteQuestion(Integer id) {
        Question q = questionRepo.findById(id)
                .orElseThrow(() -> new QuestionNotFoundException("Question not found with id: " + id));
        questionRepo.deleteById(id);
    }

    public QuestionResponse updateQuestion(Integer id, UpdateQuestionRequest request) {
        Question q= questionRepo.findById(id)
                .orElseThrow(() ->
                        new QuestionNotFoundException("Question not found with id: " + id));

        q.setDescription(request.getDescription());
        q.setDifficulty(request.getDifficulty());
        q.setCorrectAnswer(request.getCorrectAnswer());
        q.setOption1(request.getOption1());
        q.setOption2(request.getOption2());
        q.setOption3(request.getOption3());
        q.setOption4(request.getOption4());

        Question updated=questionRepo.save(q);
        return toResponse(updated);
    }

    public QuestionAnswerResponse getAnswerById(Integer id) {
        Question q= questionRepo.findById(id)
                .orElseThrow(()->
                        new QuestionNotFoundException("Question not found with id: " + id));

        return new QuestionAnswerResponse(q.getId(),q.getCorrectAnswer());

    }

    public List<QuestionAnswerResponse> getAnswers(List<Integer> questionIds) {
        return questionRepo.findAllById(questionIds)
                .stream()
                .map(q->new QuestionAnswerResponse(q.getId(),q.getCorrectAnswer()))
                .toList();
    }
}
