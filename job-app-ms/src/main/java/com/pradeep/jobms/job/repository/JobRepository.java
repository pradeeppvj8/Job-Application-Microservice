package com.pradeep.jobms.job.repository;

import com.pradeep.jobms.job.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job, Long> {
}
