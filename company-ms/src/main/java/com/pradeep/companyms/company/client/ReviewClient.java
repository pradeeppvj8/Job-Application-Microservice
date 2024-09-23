package com.pradeep.companyms.company.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("REVIEW-MS")
public interface ReviewClient {

    @GetMapping("/reviews/average-rating")
    double getAverageRating(@RequestParam Long companyId);
}
