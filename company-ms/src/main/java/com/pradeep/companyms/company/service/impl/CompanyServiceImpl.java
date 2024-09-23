package com.pradeep.companyms.company.service.impl;

import com.pradeep.companyms.company.client.ReviewClient;
import com.pradeep.companyms.company.dto.ReviewMessage;
import com.pradeep.companyms.company.model.Company;
import com.pradeep.companyms.company.repository.CompanyRepository;
import com.pradeep.companyms.company.service.interfaces.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {
    private final CompanyRepository companyRepository;
    private final ReviewClient reviewClient;

    @Override
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public boolean updateCompany(Company company, Long id) {
        Optional<Company> companyOpt = companyRepository.findById(id);

        if (companyOpt.isPresent()) {
            Company existingCompany = companyOpt.get();
            existingCompany.setDescription(company.getDescription());
            existingCompany.setName(company.getName());
            companyRepository.save(existingCompany);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void createCompany(Company company) {
        companyRepository.save(company);
    }

    @Override
    public boolean deleteCompany(Long id) {
        if (companyRepository.existsById(id)) {
            companyRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Company getCompanyById(Long id) {
        return companyRepository.findById(id).orElse(null);
    }

    @Override
    public void updateCompanyRating(ReviewMessage reviewMessage) {
        Company company = companyRepository.findById(reviewMessage.getCompanyId())
                .orElseThrow(() -> new RuntimeException("Company not found with ID - " + reviewMessage.getCompanyId()));
        double averageRating = reviewClient.getAverageRating(company.getId());
        company.setRating(averageRating);
        companyRepository.save(company);
    }
}
