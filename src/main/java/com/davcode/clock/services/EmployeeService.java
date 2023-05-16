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
import java.util.Optional;
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

    public void addEmployee(Long companyId, EmployeeJson employeeJson){
        Employee employee = DtoMapper.employeeJsonToObj(employeeJson);
        employee = DtoMapper.addCompanyToExistingEmployee(
                employee,
                companyService.getById(companyId));
        if (employee.getHourlySalary() <= 0)
            employee.setHourlySalary((long)calculateHourlySalary(employee));
        if (employee.getAssignedStartTime().isAfter(employee.getAssignedEndTime()))
            throw new Exceptions.StartTimeIsAfterEndTimeException("Error in times");
        employeeRepository.save(employee);
    }

    public void addEmployee(Employee employee){
        employeeRepository.save(employee);
    }

    public EmployeeResponse getEmployee(Long companyId,Long id){
        Optional<Employee> emp = employeeRepository.findById(id);
        if (emp.isPresent()){
            Employee employee = emp.get();
            if (employee.getCompany().getId().equals(companyId))
                return DtoMapper.employeeToDto(employee);
        }
        throw new Exceptions.EmployeeNotFoundException("No employee found in company");
    }

    public void updateEmployee(Long companyId, Long id, EmployeeJson employeeJson){
        employeeRepository.findById(id).ifPresent(emp ->{
            if (!emp.getCompany().getId().equals(companyId))
                throw new Exceptions.EmployeeNotFoundException("No employee in company with id " + id);

            Employee employee = DtoMapper.employeeJsonToObj(employeeJson);
            employeeRepository.save(
                emp.toBuilder()
                    .internalEmployeeId(employee.getInternalEmployeeId())
                    .organizationId(employee.getOrganizationId())
                    .groupId(employee.getGroupId())
                    .email(employee.getEmail())
                    .positionId(employee.getPositionId())
                    .assignedStartTime(employee.getAssignedStartTime())
                    .assignedEndTime(employee.getAssignedEndTime())
                    .monthlySalary(employee.getMonthlySalary())
                    .hourlySalary(employee.getHourlySalary())
                    .contractType(employee.getContractType())
                    .build()
            );
        } );
    }

    public void deleteEmployee(Long companyId, Long id){
        if (getEmployee(companyId,id) != null)
            employeeRepository.deleteById(id);
    }

    public List<EmployeeResponse> getAllEmployeesFromCompany(Long companyId){
        List<EmployeeResponse> employeeList = employeeRepository.findAllByCompany_Id(companyId)
                .stream()
                .map(e -> DtoMapper.employeeToDto(e))
                .collect(Collectors.toList());
        if (employeeList.isEmpty())
            throw new Exceptions.NoEmployeesInCompany("No employees found in company with id " + companyId);
        return employeeList;
    }

    public EmployeeResponse getByInternalEmployeeId(Long companyId, Long id){
        Employee employee = employeeRepository.findByInternalEmployeeId(id);
        if (employee.getCompany().getId().equals(companyId))
            return DtoMapper.employeeToDto(employee);
        throw new Exceptions.EmployeeNotFoundException("No employee found in company with internal id " + id);
    }

    public double calculateHourlySalary(Employee employee){
        long workingHoursEmp = employee.getAssignedStartTime().until(employee.getAssignedEndTime(), ChronoUnit.HOURS);
        double workingHours = workingHoursEmp * 5; //5 days of week
        workingHours *= 4; // 4 weeks a month
        return employee.getMonthlySalary()/workingHours;
    }


}
