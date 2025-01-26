package com.trillionares.tryit.product.presentation.dto;

import com.trillionares.tryit.product.domain.model.recruitment.Recruitment;
import com.trillionares.tryit.product.domain.model.recruitment.RecruitmentItem;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecruitmentToRecruitmentItemDto {
    private UUID recruitmentId;
    private UUID userId;
    private UUID productId;
    private String recruitmentStatus;

    public static RecruitmentItem from(Recruitment recruitment) {
        return RecruitmentItem.builder()
                .recruitmentId(recruitment.getRecruitmentId())
                .userId(recruitment.getUserId())
                .productId(recruitment.getProductId())
                .recruitmentStatus(recruitment.getRecruitmentStatus().toString())
                .build();
    }
}
