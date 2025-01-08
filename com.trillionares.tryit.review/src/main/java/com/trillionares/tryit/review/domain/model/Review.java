package com.trillionares.tryit.review.domain.model;

import com.trillionares.tryit.review.domain.common.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import java.util.UUID;

@Entity
@Getter
@Table(name = "p_review", schema = "review")
@SQLDelete(sql = "UPDATE p_review SET is_deleted = true WHERE review_id=?")
@SQLRestriction(value = "is_deleted = false")
@Builder(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Review extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "review_id")
    private UUID reviewId;

    @Column(name = "user_id", nullable = false)
    private UUID userId;

    @Column(name = "product_id", nullable = false)
    private UUID productId;

    @Column(name = "review_title", nullable = false)
    private String reviewTitle;

    @Column(name = "review_content", columnDefinition = "TEXT", nullable = false)
    private String reviewContent;

    @Column(name = "review_score", nullable = false)
    private int reviewScore;

    @Column(name = "review_img_url", nullable = false)
    private String reviewImgUrl;

    public void update(String reviewTitle, String reviewContent, int reviewScore, String reviewImgUrl) {
        this.reviewTitle = reviewTitle;
        this.reviewContent = reviewContent;
        this.reviewScore = reviewScore;
        this.reviewImgUrl = reviewImgUrl;
    }

    public static Review of(UUID userId, UUID productId, String reviewTitle, String reviewContent, int reviewScore, String reviewImgUrl) {
        return Review.builder()
                .userId(userId)
                .productId(productId)
                .reviewTitle(reviewTitle)
                .reviewContent(reviewContent)
                .reviewScore(reviewScore)
                .reviewImgUrl(reviewImgUrl)
                .build();
    }
}
