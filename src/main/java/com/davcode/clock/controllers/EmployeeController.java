package com.davcode.clock.controllers;

import com.davcode.clock.mappers.json.EmployeeJson;
import com.davcode.clock.models.Employee;
import com.davcode.clock.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    public void addEmployee(@RequestBody EmployeeJson employee){
        employeeService.addEmployee(employee);
    }

    @GetMapping(path = "/getAll")
    public List<Employee> getAll(){
        return Collections.emptyList();
    }
}
