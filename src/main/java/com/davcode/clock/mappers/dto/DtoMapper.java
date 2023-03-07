package com.davcode.clock.mappers.dto;

import com.davcode.clock.models.Clock;
import com.davcode.clock.models.Company;
import com.davcode.clock.models.Employee;
import com.davcode.clock.models.User;

public class DtoMapper {

    public static EmployeeResponse employeeToDto(Employee employee){
        EmployeeResponse employeeResponse = new EmployeeResponse();
        employeeResponse.setId(employee.getEmployeeId());
        employeeResponse.setEmail(employee.getEmail());
        employeeResponse.setInternalId(employee.getInternalEmployeeId());
        employeeResponse.setName(employee.getFirstName() + " " + employee.getLastName());

        return employeeResponse;
    }

    public static UserResponse UserToDto(User user){
        UserResponse userResponse = new UserResponse();
        userResponse.setUserName(user.getUserName());
        userResponse.setId(user.getId());
        userResponse.setEmployeeId(user.getEmployee().getEmployeeId());
        userResponse.setRole(user.getRole());
        return userResponse;
    }

    public static CompanyResponse CompanyToDto(Company company){
        CompanyResponse companyResponse = new CompanyResponse();
        companyResponse.setCompanyName(company.getCompanyName());
        companyResponse.setId(company.getCompanyId());
        companyResponse.setTimeZone(company.getTimeZone());
        return companyResponse;
    }

    public static ClockResponse clockToDto(Clock clock){
        ClockResponse clockResponse = new ClockResponse();
        clockResponse.setClockId(clock.getClockId());
        clockResponse.setActiveDate(clock.getActiveDate());
        clockResponse.setActiveFlag(clock.isActiveFlag());
        clockResponse.setEndTime(clock.getEndTime());
        clockResponse.setStartTime(clock.getStartTime());
        clockResponse.setUserName(clock.getUser().getUserName());
        return clockResponse;
    }

}
