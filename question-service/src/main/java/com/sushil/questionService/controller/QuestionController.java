package com.sushil.questionService.controller;


import com.sushil.questionService.QuestionServiceApplication;
import com.sushil.questionService.dto.QuestionResponse;
import com.sushil.questionService.model.Question;
import com.sushil.questionService.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @PostMapping("add")
    public ResponseEntity<Question> addQuestion(@RequestBody Question question){
        Question saved= questionService.addQuestion(question);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping("getAll")
    public ResponseEntity<List<QuestionResponse>> getAllQuetions(){
        return ResponseEntity.ok(questionService.getAllQuestions());
    }

    @GetMapping("get/{id}")
    public ResponseEntity<QuestionResponse> getQuestionById(@PathVariable Integer id){
        return ResponseEntity.ok(questionService.getQuestionById(id));
    }

    @GetMapping("topic/{topic}")
    public ResponseEntity<List<QuestionResponse>> getQuestionsByTopic(@PathVariable String topic){
        return ResponseEntity.ok(questionService.getQuestionsByTopic(topic));
    }

    @GetMapping("difficulty/{difficulty}")
    public ResponseEntity<List<QuestionResponse>> getQuestionsByDifficulty(@PathVariable String difficulty){
        return ResponseEntity.ok(questionService.getQuestionsByDifficulty(difficulty));
    }

    @GetMapping("filter")
    public ResponseEntity<List<QuestionResponse>> filter(@RequestParam String topic,@RequestParam String difficulty){
        return ResponseEntity.ok(questionService.filter(topic,difficulty));
    }

    @GetMapping("random")
    public ResponseEntity<List<QuestionResponse>> getRandomQuestions(@RequestParam String count){
        return ResponseEntity.ok(questionService.getRandomQuestions(count));
    }
}
