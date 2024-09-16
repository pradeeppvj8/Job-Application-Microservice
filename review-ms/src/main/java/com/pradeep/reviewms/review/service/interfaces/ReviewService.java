package com.pradeep.reviewms.review.service.interfaces;

import com.pradeep.reviewms.review.model.Review;

import java.util.List;

public interface ReviewService {
    List<Review> getAllReviewsForCompany(Long companyId);

    boolean addReview(Long companyId, Review review);

    Review getReview(Long reviewId);

    boolean updateReview(Long reviewId, Review review);

    boolean deleteReview(Long reviewId);
}
