package com.sushil.evaluationservice.controller;

import com.sushil.evaluationservice.dto.SubmitRequest;
import com.sushil.evaluationservice.dto.SubmitResponse;
import com.sushil.evaluationservice.service.EvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("evaluation")
public class EvaluationController {

    @Autowired
    private EvaluationService evaluationService;

    @PostMapping("submit")
    public ResponseEntity<SubmitResponse> submit(@RequestBody SubmitRequest request) {
        return ResponseEntity.ok(evaluationService.submit(request));
    }
}
