package com.pradeep.jobms.job.service.impl;

import com.pradeep.jobms.job.clients.CompanyClient;
import com.pradeep.jobms.job.clients.ReviewClient;
import com.pradeep.jobms.job.dto.JobDTO;
import com.pradeep.jobms.job.exception.JobNotFoundException;
import com.pradeep.jobms.job.external.Company;
import com.pradeep.jobms.job.external.Review;
import com.pradeep.jobms.job.mapper.JobMapper;
import com.pradeep.jobms.job.model.Job;
import com.pradeep.jobms.job.repository.JobRepository;
import com.pradeep.jobms.job.service.interfaces.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JobServiceImpl implements JobService {
    private final JobRepository jobRepository;
    private final CompanyClient companyClient;
    private final ReviewClient reviewClient;

    @Override
    public List<JobDTO> fetchAllJobs() {
        List<Job> jobs = jobRepository.findAll();
        return jobs.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public JobDTO convertToDTO(Job job) {
        Company company = companyClient.getCompany(job.getCompanyId());
        List<Review> reviews = reviewClient.getReviews(job.getCompanyId());
        return JobMapper.mapJobToJobDTO(job, company, reviews);
    }

    @Override
    public void createJob(Job job) {
        jobRepository.save(job);
    }

    @Override
    public JobDTO findJobById(Long id) {
        Job job = jobRepository.findById(id).orElseThrow(() -> new JobNotFoundException("Job with ID - " + id + " not found"));
        return convertToDTO(job);
    }

    @Override
    public boolean deleteJobById(Long id) {
        try {
            jobRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean updateJobById(Long id, Job updatedJob) {
        Optional<Job> jobOpt = jobRepository.findById(id);
        if (jobOpt.isPresent()) {
            Job job = jobOpt.get();
            job.setTitle(updatedJob.getTitle());
            job.setDescription(updatedJob.getDescription());
            job.setLocation(updatedJob.getLocation());
            job.setMinSalary(updatedJob.getMinSalary());
            job.setMaxSalary(updatedJob.getMaxSalary());
            jobRepository.save(job);
            return true;
        }
        return false;
    }
}
