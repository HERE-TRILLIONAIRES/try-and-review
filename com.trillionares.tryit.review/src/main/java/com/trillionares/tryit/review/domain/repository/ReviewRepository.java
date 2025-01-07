package com.trillionares.tryit.review.domain.repository;

import com.trillionares.tryit.review.domain.model.Review;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository {

    Review save(Review review);
}
