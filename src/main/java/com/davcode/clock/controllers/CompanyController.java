package com.davcode.clock.controllers;

import com.davcode.clock.models.Company;
import com.davcode.clock.services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/company")
public class CompanyController {
    private final CompanyService companyService;

    @Autowired
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping(path = "/getAll")
    public List<Company> getAll(){
        return companyService.getAll();
    }

    @GetMapping(path = "/{id}")
    public Company getCompany(@PathVariable("id") Long id){
        return companyService.getById(id);
    }

    @PostMapping
    public void postCompany(@RequestBody Company company){
        companyService.postCompany(company);
    }

    @PutMapping(path = "/deactivate/{companyId}")
    public void deactivate(@PathVariable Long companyId){
        companyService.changeActivationStatus(companyId,false);
    }

    @PutMapping(path = "/reactivate/{companyId}")
    public void reactivate(@PathVariable Long companyId){
        companyService.changeActivationStatus(companyId,true);
    }

    @PutMapping(path = "/updateTimeZone/{companyId}")
    public void updateTimeZone(@PathVariable Long companyId,
                               @RequestBody String timeZone){
        companyService.updateTimeZone(companyId,timeZone);
    }

    @PutMapping(path = "/update")
    public void updateCompany(@RequestBody Company company){
        companyService.updateCompany(company);
    }

    @DeleteMapping(path = "/{companyId}")
    public void deleteCompany(@PathVariable Long companyId){
        companyService.deleteCompany(companyId);
    }






}
