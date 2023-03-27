package com.davcode.clock.services;

import com.davcode.clock.exceptions.Exceptions;
import com.davcode.clock.mappers.dto.CompanyResponse;
import com.davcode.clock.mappers.dto.DtoMapper;
import com.davcode.clock.models.Company;
import com.davcode.clock.models.User;
import com.davcode.clock.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.TimeZone;
import java.util.stream.Collectors;


@Service
public class CompanyService{

    private final CompanyRepository companyRepository;
    private final UserService userService;

    @Autowired
    public CompanyService(CompanyRepository companyRepository, UserService userService) {
        this.companyRepository = companyRepository;
        this.userService = userService;
    }

    public List<CompanyResponse> getAll(){
         List<Company> companies = companyRepository.findAll();
         return companies.stream().map(c -> DtoMapper.CompanyToDto(c)).collect(Collectors.toList());
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

   public void setActivationStatus(Long companyId, boolean activationStatus){
        Company company = companyRepository.findById(companyId).get();
        company.setActive(activationStatus);
        if (!activationStatus)
            userService.getUsersFromCompany(companyId).forEach(user -> {
                userService.updateActiveFlag(user.getId(), false);
            });
        companyRepository.save(company);
    }

    public void setEmailConfirmation(Long id, boolean emailConfirmation){
        Optional<Company> company = companyRepository.findById(id);
        if (!company.isPresent())
            throw new Exceptions.CompanyNotFoundException("No such Company with id " + id);
        List<User> users = userService.getUsersFromCompany(id);
        users.forEach(user -> {
            user.setEmailConfirmationRequired(emailConfirmation);
            userService.updateUser(user);
        });
    }

    public void setAutoScheduleAllowed(Long id,boolean autoScheduleAllowed){
        Optional<Company> company = companyRepository.findById(id);
        if (!company.isPresent())
            throw new Exceptions.CompanyNotFoundException("No such Company with id " + id);
        List<User> users = userService.getUsersFromCompany(id);
        users.forEach(user -> {
            user.setAutoScheduleAllowed(autoScheduleAllowed);
            userService.updateUser(user);
        });
    }

}
