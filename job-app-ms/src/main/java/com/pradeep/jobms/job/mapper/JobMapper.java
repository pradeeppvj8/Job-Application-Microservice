package com.pradeep.jobms.job.mapper;

import com.pradeep.jobms.job.dto.JobDTO;
import com.pradeep.jobms.job.external.Company;
import com.pradeep.jobms.job.external.Review;
import com.pradeep.jobms.job.model.Job;

import java.util.List;

public class JobMapper {
    public static JobDTO mapJobToJobDTO(Job job, Company company, List<Review> reviews) {
        JobDTO jobDTO = new JobDTO();
        jobDTO.setId(job.getId());
        jobDTO.setTitle(job.getTitle());
        jobDTO.setLocation(job.getLocation());
        jobDTO.setDescription(job.getDescription());
        jobDTO.setMaxSalary(job.getMaxSalary());
        jobDTO.setMinSalary(job.getMinSalary());
        jobDTO.setCompany(company);
        jobDTO.setReviews(reviews);
        return jobDTO;
    }
}
