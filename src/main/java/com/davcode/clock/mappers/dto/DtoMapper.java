package com.davcode.clock.mappers.dto;

import com.davcode.clock.models.Clock;
import com.davcode.clock.models.Company;
import com.davcode.clock.models.Employee;
import com.davcode.clock.models.User;

public class DtoMapper {

    public static EmployeeDto employeeToDto(Employee employee){
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setId(employee.getEmployeeId());
        employeeDto.setEmail(employee.getEmail());
        employeeDto.setInternalId(employee.getInternalEmployeeId());
        employeeDto.setName(employee.getFirstName() + " " + employee.getLastName());

        return employeeDto;
    }

    public static UserDto UserToDto(User user){
        UserDto userDto = new UserDto();
        userDto.setUserName(user.getUserName());
        userDto.setId(user.getUserId());
        userDto.setEmployeeId(user.getEmployee().getEmployeeId());
        userDto.setRole(user.getRole());
        return userDto;
    }

    public static CompanyDto CompanyToDto(Company company){
        CompanyDto companyDto = new CompanyDto();
        companyDto.setCompanyName(company.getCompanyName());
        companyDto.setId(company.getCompanyId());
        companyDto.setTimeZone(company.getTimeZone());
        return companyDto;
    }

    public static ClockDto clockToDto(Clock clock){
        ClockDto clockDto = new ClockDto();
        clockDto.setClockId(clock.getClockId());
        clockDto.setActiveDate(clock.getActiveDate());
        clockDto.setActiveFlag(clock.isActiveFlag());
        clockDto.setEndTime(clock.getEndTime());
        clockDto.setStartTime(clock.getStartTime());
        clockDto.setUserName(clock.getUser().getUserName());
        return clockDto;
    }

}
