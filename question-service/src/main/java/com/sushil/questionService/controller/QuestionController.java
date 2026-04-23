package com.sushil.questionService.controller;


import com.sushil.questionService.QuestionServiceApplication;
import com.sushil.questionService.model.Question;
import com.sushil.questionService.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @PostMapping("add")
    public Question addQuestion(@RequestBody Question question){
        return questionService.addQuestion(question);
    }

    @GetMapping("getAll")
    public List<Question> getAllQuetions(){
        return questionService.getAllQuestions();
    }

    @GetMapping("get/{id}")
    public Question getQuestionById(@PathVariable Integer id){
        return questionService.getQuestionById(id);
    }

    @GetMapping("topic/{topic}")
    public List<Question> getQuestionsByTopic(@PathVariable String topic){
        return questionService.getQuestionsByTopic(topic);
    }

    @GetMapping("difficulty/{difficulty}")
    public List<Question> getQuestionsByDifficulty(@PathVariable String difficulty){
        return questionService.getQuestionsByDifficulty(difficulty);
    }

    @GetMapping("filter")
    public List<Question> filter(@RequestParam String topic,@RequestParam String difficulty){
        return questionService.filter(topic,difficulty);
    }

    @GetMapping("random")
    public List<Question> getRandomQuestions(@RequestParam String count){
        return questionService.getRandomQuestions(count);
    }
}
