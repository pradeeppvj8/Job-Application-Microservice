package com.pradeep.jobms.job.clients;

import com.pradeep.jobms.job.external.Company;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "COMPANY-MS")
public interface CompanyClient {

    @GetMapping("/companies/get-company/{id}")
    Company getCompany(@PathVariable Long id);
}
