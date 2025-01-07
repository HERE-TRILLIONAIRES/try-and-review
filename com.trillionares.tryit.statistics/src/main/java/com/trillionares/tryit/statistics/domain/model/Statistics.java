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

    @Column(name = "product_id", nullable = false)
    private UUID productId;

    @Column(name = "highest_score", nullable = false)
    private int highestScore;

    @Column(name = "lowest_score", nullable = false)
    private int lowestScore;

    @Column(name = "review_count", nullable = false)
    private int reviewCount;

    @Column(name = "duration_time", nullable = false)
    private long durationTime;

    public static Statistics of(UUID productId, int highestScore, int lowestScore, int reviewCount, long durationTime) {
        return Statistics.builder()
                .productId(productId)
                .highestScore(highestScore)
                .lowestScore(lowestScore)
                .reviewCount(reviewCount)
                .durationTime(durationTime)
                .build();
    }
}