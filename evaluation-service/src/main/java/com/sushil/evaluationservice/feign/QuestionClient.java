package com.sushil.evaluationservice.feign;

import com.sushil.evaluationservice.dto.QuestionAnswerRequest;
import com.sushil.evaluationservice.dto.QuestionAnswerResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient("question-service")
public interface QuestionClient {

    @PostMapping("/question/answers")
    List<QuestionAnswerResponse> getAnswers(@RequestBody QuestionAnswerRequest request);

}
