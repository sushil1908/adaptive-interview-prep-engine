package com.sushil.recommendation_service.feign;

import com.sushil.recommendation_service.dto.QuestionResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("question-service")
public interface QuestionClient {

    @GetMapping("/question/get/{id}")
    QuestionResponse getQuestionById(@PathVariable Integer id);
}