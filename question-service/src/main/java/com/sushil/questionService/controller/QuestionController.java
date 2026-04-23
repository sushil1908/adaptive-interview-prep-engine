package com.sushil.questionService.controller;


import com.sushil.questionService.QuestionServiceApplication;
import com.sushil.questionService.model.Question;
import com.sushil.questionService.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @PostMapping("add")
    public Question addQuestion(@RequestBody Question question){
        return questionService.addQuestion(question);
    }
}
