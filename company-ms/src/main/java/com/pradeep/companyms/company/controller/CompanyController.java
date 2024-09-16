package com.pradeep.companyms.company.controller;

import com.pradeep.companyms.company.model.Company;
import com.pradeep.companyms.company.service.interfaces.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
@RequiredArgsConstructor
public class CompanyController {
    private final CompanyService companyService;

    @GetMapping("/get-all")
    public ResponseEntity<List<Company>> getAllCompanies() {
        return ResponseEntity.ok(companyService.getAllCompanies());
    }

    @PutMapping("/update-company/{id}")
    public ResponseEntity<String> updateCompany(@PathVariable Long id,
                                                @RequestBody Company company) {
        boolean isUpdated = companyService.updateCompany(company, id);

        if (isUpdated) {
            return ResponseEntity.ok("Company updated successfully");
        } else {
            return new ResponseEntity<>("Company updating failed", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/create-company")
    public ResponseEntity<String> createCompany(@RequestBody Company company) {
        companyService.createCompany(company);
        return new ResponseEntity<>("Company created successfully", HttpStatus.CREATED);
    }

    @DeleteMapping("/delete-company/{id}")
    public ResponseEntity<String> deleteCompany(@PathVariable Long id) {
        boolean isDeleted = companyService.deleteCompany(id);

        if (isDeleted) {
            return new ResponseEntity<>("Company was deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Deleting company failed", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/get-company/{id}")
    public ResponseEntity<Company> getCompanyById(@PathVariable Long id) {
        Company company = companyService.getCompanyById(id);

        if (company != null) {
            return new ResponseEntity<>(company, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
