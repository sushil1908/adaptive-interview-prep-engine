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
}
