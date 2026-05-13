package com.sushil.evaluationservice.service;

import com.sushil.evaluationservice.dto.*;
import com.sushil.evaluationservice.event.AttemptCompletedEvent;
import com.sushil.evaluationservice.feign.QuestionClient;
import com.sushil.evaluationservice.kafka.KafkaProducer;
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
    QuestionClient questionClient;

    @Autowired
    private KafkaProducer  kafkaProducer;

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

            QuestionAnswerRequest questionAnswerRequest=new QuestionAnswerRequest();
            questionAnswerRequest.setQuestionIds(questionIds);

        List<QuestionAnswerResponse> answers=questionClient.getAnswers(questionAnswerRequest);

        Map<Integer, String> correctMap = answers.stream()
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

        AttemptCompletedEvent event=new AttemptCompletedEvent(
                request.getUserId(), score,total);

        kafkaProducer.publishAttemptCompletedEvent(event);

        return new SubmitResponse(score,total);
    }


    public List<Attempt> getHistory(Integer userId) {
        return attemptRepo.findByUserId(userId);
    }
}
