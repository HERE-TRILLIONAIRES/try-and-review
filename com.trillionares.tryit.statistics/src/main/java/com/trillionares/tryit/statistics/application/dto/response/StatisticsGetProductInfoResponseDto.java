package com.trillionares.tryit.statistics.application.dto.response;

import lombok.Getter;

import java.util.List;
import java.util.UUID;

@Getter
public class StatisticsGetProductInfoResponseDto {
    private UUID productId;
    private UUID userId;
    private String seller;
    private String name;
    private String content;
    private String category;
    private String mainProductImgUrl;
    private List<String> contentImgUrlList;
}
