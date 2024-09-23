package com.pradeep.reviewms.review.controller;

import com.pradeep.reviewms.review.messaging.ReviewMessageProducer;
import com.pradeep.reviewms.review.model.Review;
import com.pradeep.reviewms.review.service.interfaces.ReviewService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;
    private final ReviewMessageProducer reviewMessageProducer;

    @GetMapping("/get-all-reviews")
    public ResponseEntity<List<Review>> getAllReviewsForCompany(@RequestParam Long companyId) {
        List<Review> reviews = reviewService.getAllReviewsForCompany(companyId);
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }

    @PostMapping("/add-review")
    public ResponseEntity<String> addReview(@RequestParam Long companyId, @RequestBody Review review) {
        boolean isReviewAdded = reviewService.addReview(companyId, review);

        if (isReviewAdded) {
            reviewMessageProducer.sendMessage(review);
            return new ResponseEntity<>("Review added successfully", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Review not added", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/get-review/{reviewId}")
    public ResponseEntity<Review> getReview(@PathVariable Long reviewId) {
        return ResponseEntity.ok(reviewService.getReview(reviewId));
    }

    @PutMapping("/update-review/{reviewId}")
    public ResponseEntity<String> updateReview(@PathVariable Long reviewId, @RequestBody Review review) {
        boolean isReviewUpdated = reviewService.updateReview(reviewId, review);

        if (isReviewUpdated) {
            return new ResponseEntity<>("Review Updated successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Review Could Not Updated", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete-review/{reviewId}")
    @Transactional
    public ResponseEntity<String> deleteReview(@PathVariable Long reviewId) {
        boolean isReviewDeleted = reviewService.deleteReview(reviewId);

        if (isReviewDeleted) {
            return new ResponseEntity<>("Review Deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Review Could Not Deleted", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/average-rating")
    public double getAverageRating(@RequestParam Long companyId) {
        List<Review> reviews = reviewService.getAllReviewsForCompany(companyId);
        return reviews.stream().mapToDouble(Review::getRating).average().orElse(0.0);
    }
}
