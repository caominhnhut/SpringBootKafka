package com.sts.event.producer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaProducer<T> {

    private final KafkaTemplate<String, T> kafkaTemplate;

    public void sendMessage(String topic, T event) {
        ProducerRecord<String, T> producerRecord = new ProducerRecord<>(topic, event);
        CompletableFuture<SendResult<String, T>> completableFuture = kafkaTemplate.send(producerRecord);
        log.info("Sending kafka message on topic {}", topic);

        completableFuture.whenComplete((result, ex) -> {
            if (ex == null) {
                log.info("Kafka message successfully sent on topic {} and value {}", topic, result.getProducerRecord().value().toString());
            } else {
                log.error("An error occurred while sending kafka message for event with value {}", producerRecord);
            }
        });
    }

}
