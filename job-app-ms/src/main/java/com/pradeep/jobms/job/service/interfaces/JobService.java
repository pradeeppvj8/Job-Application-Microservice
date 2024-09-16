package com.pradeep.jobms.job.service.interfaces;


import com.pradeep.jobms.job.model.Job;

import java.util.List;

public interface JobService {
    List<Job> fetchAllJobs();

    void createJob(Job job);

    Job findJobById(Long id);

    boolean deleteJobById(Long id);

    boolean updateJobById(Long id, Job updatedJob);
}
