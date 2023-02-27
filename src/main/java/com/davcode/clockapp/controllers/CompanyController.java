package com.davcode.clockapp.controllers;


import com.davcode.clockapp.models.Company;
import com.davcode.clockapp.services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/company")
public class CompanyController {

    private final CompanyService companyService;

    @Autowired
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping(path = "/{companyId}")
    public Company getCompany(@PathVariable("companyId") Long companyId){
        return null;
    }

    @PostMapping()
    public void postCompany(){

    }

    @DeleteMapping()
    public void deleteCompany(){

    }

    @PutMapping(path = "/{companyId}")
    public void putCompany(){

    }
}
