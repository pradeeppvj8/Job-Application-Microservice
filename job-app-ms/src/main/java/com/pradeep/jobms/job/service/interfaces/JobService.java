package com.pradeep.jobms.job.service.interfaces;


import com.pradeep.jobms.job.dto.JobDTO;
import com.pradeep.jobms.job.model.Job;

import java.util.List;

public interface JobService {
    List<JobDTO> fetchAllJobs();

    void createJob(Job job);

    JobDTO findJobById(Long id);

    boolean deleteJobById(Long id);

    boolean updateJobById(Long id, Job updatedJob);
}
