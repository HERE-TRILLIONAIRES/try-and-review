package com.trillionares.tryit.trial.jhtest.presentation.dto.common.kafka;

import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class KafkaMessage {

    private UUID id;           // 고유 ID (UUID)
    private String payload;      // 실제 메시지 데이터
    private LocalDateTime timestamp;  // 발행 시간

    public static KafkaMessage from(String sendPayloadJson) {
        return KafkaMessage.builder()
                .id(UUID.randomUUID())
                .payload(sendPayloadJson)
                .timestamp(LocalDateTime.now())
                .build();
    }
}