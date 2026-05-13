package com.sushil.evaluationservice.kafka;

import com.sushil.evaluationservice.event.AttemptCompletedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.kafka.core.KafkaTemplate;

@Service
public class KafkaProducer {
    private static final String TOPIC = "attempt-completed";

    @Autowired
    private KafkaTemplate<String, AttemptCompletedEvent> kafkaTemplate;

    public void publishAttemptCompletedEvent(AttemptCompletedEvent event) {
        kafkaTemplate.send(TOPIC,event);
        System.out.println("Published event to Kafka: " + event);
    }
}
