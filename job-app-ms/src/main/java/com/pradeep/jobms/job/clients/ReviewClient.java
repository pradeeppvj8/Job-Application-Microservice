package com.pradeep.jobms.job.clients;

import com.pradeep.jobms.job.external.Review;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "REVIEW-MS" , url = "${review-service.url}")
public interface ReviewClient {

    @GetMapping("/reviews/get-all-reviews")
    List<Review> getReviews(@RequestParam(name = "companyId") Long companyId);
}
