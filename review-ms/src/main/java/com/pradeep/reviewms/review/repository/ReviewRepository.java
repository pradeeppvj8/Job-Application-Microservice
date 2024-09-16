package com.pradeep.reviewms.review.repository;

import com.pradeep.reviewms.review.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByCompanyId(Long companyId);

    Review findByIdAndCompanyId(Long reviewId, Long companyId);

    void deleteByIdAndCompanyId(Long reviewId, Long companyId);

    boolean existsByIdAndCompanyId(Long reviewId, Long companyId);
}
