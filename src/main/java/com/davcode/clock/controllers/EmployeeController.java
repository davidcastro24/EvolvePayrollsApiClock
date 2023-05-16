package com.davcode.clock.controllers;

import com.davcode.clock.mappers.dto.EmployeeResponse;
import com.davcode.clock.mappers.json.EmployeeJson;
import com.davcode.clock.models.Employee;
import com.davcode.clock.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/employee/{companyId}")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    public void addEmployee(@PathVariable Long companyId, @RequestBody EmployeeJson employee){
        employeeService.addEmployee(companyId,employee);
    }

    @GetMapping(path = "/getAll")
    public List<EmployeeResponse> getAllFromCompany(@PathVariable Long companyId){
        return employeeService.getAllEmployeesFromCompany(companyId);
    }

    @GetMapping(path = "/{employeeId}")
    public EmployeeResponse getEmployee(@PathVariable Long companyId, @PathVariable Long employeeId){
        return employeeService.getEmployee(companyId,employeeId);
    }

    @GetMapping(path = "/internal/{internalId}")
    public EmployeeResponse getEmployeeByInternalId(@PathVariable Long companyId, @PathVariable Long internalId){
        return employeeService.getByInternalEmployeeId(companyId,internalId);
    }

    @PutMapping(path = "/{id}")
    public void putEmployee(@PathVariable Long companyId, @PathVariable Long id, @RequestBody EmployeeJson employeeJson ){
        employeeService.updateEmployee(companyId,id,employeeJson);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteEmployee(@PathVariable Long companyId, @PathVariable Long internalId){
        employeeService.deleteEmployee(companyId,internalId);
    }


}
