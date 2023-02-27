package com.davcode.clockapp.services;

import com.davcode.clockapp.models.Company;
import com.davcode.clockapp.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {
    private final CompanyRepository companyRepository;

    @Autowired
    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public List<Company> getAll(){
        return companyRepository
    }
}
