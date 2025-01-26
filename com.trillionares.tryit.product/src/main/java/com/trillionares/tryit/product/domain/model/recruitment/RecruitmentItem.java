package com.trillionares.tryit.product.domain.model.recruitment;

import java.io.Serializable;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@RedisHash("recruitmentItem")
public class RecruitmentItem implements Serializable {
    @Id
    private UUID recruitmentId;
    private UUID userId;
    private UUID productId;
    private String recruitmentStatus;
}
