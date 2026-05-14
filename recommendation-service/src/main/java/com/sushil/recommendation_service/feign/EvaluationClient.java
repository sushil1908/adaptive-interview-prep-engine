package com.sushil.recommendation_service.feign;

import com.sushil.recommendation_service.dto.AttemptResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient("evaluation-service")
public interface EvaluationClient {

    @GetMapping("/evaluation/history/{userId}")
    List<AttemptResponse> getAttemptHistory(@PathVariable Integer userId);
}
