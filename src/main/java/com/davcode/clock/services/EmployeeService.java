package com.davcode.clock.services;

import com.davcode.clock.exceptions.Exceptions;
import com.davcode.clock.mappers.dto.DtoMapper;
import com.davcode.clock.mappers.dto.EmployeeResponse;
import com.davcode.clock.mappers.json.EmployeeJson;
import com.davcode.clock.models.Employee;
import com.davcode.clock.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final CompanyService companyService;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository, CompanyService companyService) {
        this.employeeRepository = employeeRepository;
        this.companyService = companyService;
    }

    public void addEmployee(EmployeeJson employeeJson){
        Employee employee = DtoMapper.employeeJsonToObj(employeeJson);
        employee = DtoMapper.addCompanyToExistingEmployee(
                employee,
                companyService.getById(employeeJson.getCompanyId()));
        if (employee.getHourlySalary() <= 0)
            employee.setHourlySalary((long)calculateHourlySalary(employee));
        if (employee.getAssignedStartTime().isAfter(employee.getAssignedEndTime()))
            throw new Exceptions.StartTimeIsAfterEndTimeException("Error in times");
        employeeRepository.save(employee);
    }

    public void addEmployee(Employee employee){
        employeeRepository.save(employee);
    }

    public EmployeeResponse getEmployee(Long id){
        return DtoMapper.employeeToDto(employeeRepository.findById(id).get());
    }

    public void updateEmployee(Employee employee){
        employeeRepository.save(employee);
    }

    public void deleteEmployee(Long id){
        employeeRepository.deleteById(id);
    }

    public List<EmployeeResponse> getAllEmployeesFromCompany(Long companyId){
        return employeeRepository.findAllByCompany_Id(companyId)
                .stream()
                .map(e -> DtoMapper.employeeToDto(e))
                .collect(Collectors.toList());
    }

    public double calculateHourlySalary(Employee employee){
        long workingHoursEmp = employee.getAssignedStartTime().until(employee.getAssignedEndTime(), ChronoUnit.HOURS);
        double workingHours = workingHoursEmp * 5; //5 days of week
        workingHours *= 4; // 4 weeks a month
        return employee.getMonthlySalary()/workingHours;
    }


}
