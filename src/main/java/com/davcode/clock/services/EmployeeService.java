package com.davcode.clock.services;

import com.davcode.clock.models.Employee;
import com.davcode.clock.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public void addEmployee(Employee employee){
        employeeRepository.save(employee);
    }

    public Employee getEmployee(Long id){
        return employeeRepository.findById(id).get();
    }

    public void updateEmployee(Employee employee){
        employeeRepository.save(employee);
    }

    public void deleteEmployee(Long id){
        employeeRepository.deleteById(id);
    }

    public List<Employee> getAllEmployeesFromCompany(Long companyId){
        return employeeRepository.findEmployeesByCompanyId(companyId);
    }
}