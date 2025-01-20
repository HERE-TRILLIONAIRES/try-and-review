package com.trillionares.tryit.product.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecruitmentExistAndStatusDto {
    private Boolean isExist;
    private String status;

    public static RecruitmentExistAndStatusDto of(Boolean isExist, String recruitmentStatus) {
        return RecruitmentExistAndStatusDto.builder()
                .isExist(isExist)
                .status(recruitmentStatus)
                .build();
    }
}
