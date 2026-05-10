package com.sushil.evaluationservice.service;

import com.sushil.evaluationservice.dto.*;
import com.sushil.evaluationservice.model.Attempt;
import com.sushil.evaluationservice.model.AttemptAnswer;
import com.sushil.evaluationservice.repo.AttemptAnswerRepo;
import com.sushil.evaluationservice.repo.AttemptRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class EvaluationService {

    @Autowired
    AttemptRepo attemptRepo;

    @Autowired
    AttemptAnswerRepo attemptAnswerRepo;

    @Autowired
    private RestTemplate restTemplate;

    public SubmitResponse submit(SubmitRequest request) {
        int score=0;
        int total=request.getAnswers().size();
        Attempt attempt=new  Attempt();
        attempt.setUserId(request.getUserId());
        attempt.setTotalQuestions(total);
        attempt.setCreatedAt(LocalDateTime.now());

        List<Integer> questionIds = request.getAnswers()
                .stream()
                .map(AnswerDTO::getQuestionId)
                .toList();

            String url="http://localhost:8081/question/answers";

            QuestionAnswerRequest questionAnswerRequest=new QuestionAnswerRequest();
            questionAnswerRequest.setQuestionIds(questionIds);

        ResponseEntity<QuestionAnswerResponse[]> response=restTemplate.postForEntity(url,questionAnswerRequest, QuestionAnswerResponse[].class);

        Map<Integer, String> correctMap = Arrays.stream(response.getBody())
                .collect(Collectors.toMap(
                        QuestionAnswerResponse::getQuestionId,
                        QuestionAnswerResponse::getCorrectAnswer
                ));
        for(AnswerDTO ans:request.getAnswers()){

            String correct = correctMap.get(ans.getQuestionId());

            boolean isCorrect = correct != null && correct.equals(ans.getSelectedAnswer());

            if (isCorrect) score++;
            AttemptAnswer attemptAnswer=new AttemptAnswer();
            attemptAnswer.setAttemptId(attempt.getId());
            attemptAnswer.setQuestionId(ans.getQuestionId());
            attemptAnswer.setSelectedAnswer(ans.getSelectedAnswer());
            attemptAnswer.setCorrect(isCorrect);
            attemptAnswerRepo.save(attemptAnswer);
        }
        attempt.setScore(score);
        attemptRepo.save(attempt);
        return new SubmitResponse(score,total);
    }

}
