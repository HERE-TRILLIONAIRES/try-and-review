package com.trillionares.tryit.review.infrastructure.persistence;

import com.trillionares.tryit.review.domain.model.Review;
import com.trillionares.tryit.review.domain.repository.ReviewRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ReviewRepositoryImpl extends JpaRepository <Review, UUID>, ReviewRepository {
}
