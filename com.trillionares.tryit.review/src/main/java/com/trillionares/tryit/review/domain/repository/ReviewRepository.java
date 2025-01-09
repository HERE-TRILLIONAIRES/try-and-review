package com.trillionares.tryit.review.domain.repository;

import com.trillionares.tryit.review.domain.model.Review;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ReviewRepository {

    Review save(Review review);

    Optional<Review> findById(UUID reviewId);

    void deleteById(UUID reviewId);
}
