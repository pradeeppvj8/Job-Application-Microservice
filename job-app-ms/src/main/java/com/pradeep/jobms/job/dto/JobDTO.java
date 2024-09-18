package com.pradeep.jobms.job.dto;

import com.pradeep.jobms.job.external.Company;
import com.pradeep.jobms.job.external.Review;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class JobDTO {
    private Long id;
    private String title;
    private String description;
    private String minSalary;
    private String maxSalary;
    private String location;
    private Company company;
    private List<Review> reviews;
}
