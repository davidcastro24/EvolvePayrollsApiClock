package com.davcode.clock.services;

import com.davcode.clock.models.Company;
import com.davcode.clock.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.TimeZone;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;

    @Autowired
    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public List<Company> getAll(){
        return companyRepository.findAll();
    }

    public Company getById(Long id){
        return companyRepository.findById(id).get();
    }

    public void postCompany(Company company){
        //Optional<Company> existsInDB = Optional.of(companyRepository.getReferenceById(company.getId()));
        //if (!existsInDB.isPresent())
        try {
            companyRepository.save(company);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void deleteCompany(Long id){
        companyRepository.deleteById(id);
    }

    public void updateCompany(Company company){
        companyRepository.save(company);

    }

    public void updateTimeZone(Long companyId, String timeZone){
        Company company = companyRepository.findById(companyId).get();
        company.setTimeZone(TimeZone.getTimeZone(timeZone));
        companyRepository.save(company);
    }

   public void changeActivationStatus(Long companyId, boolean activationStatus){
        Company company = companyRepository.findById(companyId).get();
        company.setActive(activationStatus);
        companyRepository.save(company);
    }

}
