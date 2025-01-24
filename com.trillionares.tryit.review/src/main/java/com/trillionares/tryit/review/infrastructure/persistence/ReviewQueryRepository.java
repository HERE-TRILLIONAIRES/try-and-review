package com.trillionares.tryit.review.infrastructure.persistence;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.trillionares.tryit.review.application.dto.response.ReviewStatisticsDataResponseDto;
import com.trillionares.tryit.review.domain.model.QReview;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReviewQueryRepository {

    private final JPAQueryFactory queryFactory;

    public List<ReviewStatisticsDataResponseDto> getReviewStatistics() {

        QReview qReview = QReview.review;

        return queryFactory
                    .select(Projections.constructor(ReviewStatisticsDataResponseDto.class,
                            qReview.productId,
                            qReview.reviewScore.max().as("highestScore"),
                            qReview.reviewScore.min().as("lowestScore"),
                            Expressions.numberTemplate(Double.class, "ROUND(AVG({0}), 1)", qReview.reviewScore).as("averageScore"),
                            qReview.count().intValue().as("reviewCount")
                    ))
                    .from(qReview)
                    .groupBy(qReview.productId)
                    .fetch();
    }
}
