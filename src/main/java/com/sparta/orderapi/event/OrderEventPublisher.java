package com.sparta.orderapi.event;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.orderapi.webdto.CreateOrderRequest;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class OrderEventPublisher {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final String topicName = "order-event";
    private final ObjectMapper objectMapper;

    public void publishOrderCreated(CreateOrderEvent createOrderEvent) {
        String sendData = null;
        try {
            sendData = objectMapper.writeValueAsString(createOrderEvent);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        log.info("Kafka message send attempt: {}", sendData);

        CompletableFuture.runAsync(() -> kafkaTemplate.send(topicName, sendData))
            .thenAccept(result -> log.info("Message sent successfully to topic: {}, partition: {}",
                topicName, Objects.requireNonNull(result).getRecordMetadata().partition()))
            .exceptionally(ex -> {
                log.error("Failed to send message to topic: {}", topicName, ex);
                return null;
            });
    }
}
