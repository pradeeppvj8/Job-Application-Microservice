package com.pradeep.companyms.company.repository;

import com.pradeep.companyms.company.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}
