package com.trillionares.tryit.statistics.domain.model;

import com.trillionares.tryit.statistics.domain.common.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Getter
@Table(name = "p_statistics", schema = "statistics")
@Builder(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Statistics extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "statistics_id")
    private UUID statisticsId;

    @Column(name = "user_id", nullable = false)
    private UUID userId;

    @Column(name = "product_id", nullable = false)
    private UUID productId;

    @Column(name = "highest_score", nullable = false)
    private Integer highestScore;

    @Column(name = "lowest_score", nullable = false)
    private Integer lowestScore;

    @Column(name = "average_score", nullable = false)
    private Double averageScore;

    @Column(name = "review_count", nullable = false)
    private Integer reviewCount;

    @Column(name = "duration_time", nullable = false)
    private Long durationTime;

    public static Statistics of(UUID userId, UUID productId, Integer highestScore, Integer lowestScore, Double averageScore,Integer reviewCount, Long durationTime) {
        return Statistics.builder()
                .userId(userId)
                .productId(productId)
                .highestScore(highestScore)
                .lowestScore(lowestScore)
                .averageScore(averageScore)
                .reviewCount(reviewCount)
                .durationTime(durationTime)
                .build();
    }
}