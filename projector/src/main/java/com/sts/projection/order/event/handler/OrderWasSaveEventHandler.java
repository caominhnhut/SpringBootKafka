package com.sts.projection.order.event.handler;

import com.sts.avro.model.OrderEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderWasSaveEventHandler {

    @KafkaListener(topics = "${kafka.order-topic}", containerFactory = "kafkaListenerContainerFactory", groupId = "${spring.kafka.consumer.group-id}")
    public void listen(Message<OrderEvent> transactionEventMessage) {
        log.info("Starting consuming from order_topic - {}", transactionEventMessage.toString());
    }

}
