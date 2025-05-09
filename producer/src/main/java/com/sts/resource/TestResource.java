package com.sts.resource;

import com.sts.avro.model.OrderEvent;
import com.sts.event.producer.KafkaProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class TestResource {

    private final KafkaProducer kafkaProducer;

    @Value("${kafka.order-topic}")
    private String topic;

    @PostMapping("/send")
    public void sendMessage() {
        kafkaProducer.sendMessage(topic, OrderEvent.newBuilder()
                .setId(UUID.randomUUID())
                .setFirstName("firstName")
                .setLastName("lastName")
                .setOrderedTime(Instant.now())
                .setStatus("status").build());
    }

}
