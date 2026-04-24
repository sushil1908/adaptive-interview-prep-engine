package com.sushil.evaluationservice.service;

import com.sushil.evaluationservice.dto.AnswerDTO;
import com.sushil.evaluationservice.dto.SubmitRequest;
import com.sushil.evaluationservice.dto.SubmitResponse;
import com.sushil.evaluationservice.model.Attempt;
import com.sushil.evaluationservice.model.AttemptAnswer;
import com.sushil.evaluationservice.repo.AttemptAnswerRepo;
import com.sushil.evaluationservice.repo.AttemptRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class EvaluationService {

    @Autowired
    AttemptRepo attemptRepo;

    @Autowired
    AttemptAnswerRepo attemptAnswerRepo;

    public SubmitResponse submit(SubmitRequest request) {
        int score=0;
        int total=request.getAnswers().size();
        Attempt attempt=new  Attempt();
        attempt.setUserId(request.getUserId());
        attempt.setTotalQuestions(total);
        attempt.setCreatedAt(LocalDateTime.now());

        for(AnswerDTO ans: request.getAnswers()) {
            // lets assume all answers are correct
            AttemptAnswer attemptAnswer=new AttemptAnswer();
            attemptAnswer.setAttemptId(attempt.getId());
            attemptAnswer.setQuestionId(ans.getQuestionId());
            attemptAnswer.setSelectedAnswer(ans.getSelectedAnswer());
            attemptAnswer.setCorrect(true);

            attemptAnswerRepo.save(attemptAnswer);
            score++;
        }
        attempt.setScore(score);
        attemptRepo.save(attempt);
        return new SubmitResponse(score,total);
    }

}
