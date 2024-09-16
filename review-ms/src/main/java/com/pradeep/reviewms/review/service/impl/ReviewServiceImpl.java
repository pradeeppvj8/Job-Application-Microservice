package com.pradeep.reviewms.review.service.impl;

import com.pradeep.reviewms.review.model.Review;
import com.pradeep.reviewms.review.repository.ReviewRepository;
import com.pradeep.reviewms.review.service.interfaces.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;

    @Override
    public List<Review> getAllReviewsForCompany(Long companyId) {
        return reviewRepository.findByCompanyId(companyId);
    }

    @Override
    public boolean addReview(Long companyId, Review review) {
        if (companyId != null) {
            review.setCompanyId(companyId);
            reviewRepository.save(review);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Review getReview(Long reviewId) {
        return reviewRepository.findById(reviewId).orElse(null);
    }

    @Override
    public boolean updateReview(Long reviewId, Review updatedReview) {
        Review review = reviewRepository.findById(reviewId).orElse(null);

        if (review != null) {
            review.setCompanyId(updatedReview.getCompanyId());
            review.setDescription(updatedReview.getDescription());
            review.setTitle(updatedReview.getTitle());
            review.setRating(updatedReview.getRating());
            reviewRepository.save(review);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean deleteReview(Long reviewId) {
        Review review = reviewRepository.findById(reviewId).orElse(null);

        if (review != null) {
            reviewRepository.delete(review);
            return true;
        } else {
            return false;
        }
    }
}
