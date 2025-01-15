package com.trillionares.tryit.product.presentation.dto.common.kafka;

import java.time.LocalDateTime;
import java.util.UUID;

public record KafkaMessage(
        UUID id,
        String payload,
        String timestamp

) {
    public static KafkaMessage of(String sendPayloadJson) {
        return new KafkaMessage(UUID.randomUUID(), sendPayloadJson, String.valueOf(LocalDateTime.now()));
    }
}