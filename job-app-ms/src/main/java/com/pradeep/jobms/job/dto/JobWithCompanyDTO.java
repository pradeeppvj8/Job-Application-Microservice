package com.pradeep.jobms.job.dto;

import com.pradeep.jobms.job.external.Company;
import com.pradeep.jobms.job.model.Job;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class JobWithCompanyDTO {
    private Job job;
    private Company company;
}
