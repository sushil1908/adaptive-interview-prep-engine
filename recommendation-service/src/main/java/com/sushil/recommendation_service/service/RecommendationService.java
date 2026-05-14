package com.sushil.recommendation_service.service;

import com.sushil.recommendation_service.dto.AttemptResponse;
import com.sushil.recommendation_service.dto.QuestionResponse;
import com.sushil.recommendation_service.event.AttemptCompletedEvent;
import com.sushil.recommendation_service.feign.EvaluationClient;
import com.sushil.recommendation_service.feign.QuestionClient;
import com.sushil.recommendation_service.model.Recommendation;
import com.sushil.recommendation_service.repo.RecommendationRepo;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecommendationService {

    @Autowired
    private EvaluationClient evaluationClient;

    @Autowired
    private QuestionClient questionClient;

    @Autowired
    private RecommendationRepo recommendationRepo;

    private final  ChatClient chatClient;
    public RecommendationService(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    public void generateRecommendation(AttemptCompletedEvent event){

        List<AttemptResponse> history=evaluationClient.getAttemptHistory(event.getUserId());
        List<Boolean> ids=event.getCorrectness();
        List<QuestionResponse> questions = event.getQuestionIds()
                .stream()
                .map(questionClient::getQuestionById)
                .collect(Collectors.toList());

        StringBuilder prompt = new StringBuilder();
        prompt.append("You are an adaptive interview preparation assistant.\n\n");
        prompt.append("User just completed an attempt:\n");
        prompt.append("Score: ").append(event.getScore())
                .append("/").append(event.getTotalQuestions()).append("\n");
        prompt.append("Questions attempted:\n");

        for(int i=0; i<questions.size(); i++){
            QuestionResponse q=questions.get(i);
            boolean correctness=event.getCorrectness().get(i);
            prompt.append("- Topic: ").append(q.getTopic())
                    .append(", Difficulty: ").append(q.getDifficulty())
                    .append(", Correct: ").append(correctness).append("\n");
        }

        prompt.append("\nUser's full attempt history summary:\n");
        for (AttemptResponse attempt : history) {
            prompt.append("- Score: ").append(attempt.getScore())
                    .append("/").append(attempt.getTotalQuestions())
                    .append(" at ").append(attempt.getCreatedAt()).append("\n");
        }

        prompt.append("\nBased on this data, provide:\n");
        prompt.append("1. The topics user should focus on\n");
        prompt.append("2. Recommended difficulty level for next attempt\n");
        prompt.append("3. Specific study tips");
        prompt.append("4. keep response concise and actionable(max 150 words)\n");

        String aiResponse=chatClient.prompt()
                .user(prompt.toString())
                .call()
                .content();

        Recommendation recommendation =new Recommendation();
        recommendation.setUserId(event.getUserId());
        recommendation.setRecommendation(aiResponse);
        recommendation.setCreatedAt(LocalDateTime.now());

        recommendationRepo.save(recommendation);
        System.out.println("Recommendation for user " + event.getUserId()+ ": " + aiResponse);

    }

    public Recommendation getLatestRecommendation(Integer userId){
        return recommendationRepo
                .findTopByUserIdOrderByCreatedAtDesc(userId)
                .orElse(null);
    }

}
