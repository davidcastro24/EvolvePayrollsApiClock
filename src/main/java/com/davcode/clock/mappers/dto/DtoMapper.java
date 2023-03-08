package com.davcode.clock.mappers.dto;

import com.davcode.clock.mappers.RequestJson;
import com.davcode.clock.mappers.json.EmployeeJson;
import com.davcode.clock.mappers.json.UserJson;
import com.davcode.clock.models.Clock;
import com.davcode.clock.models.Company;
import com.davcode.clock.models.Employee;
import com.davcode.clock.models.User;
import com.davcode.clock.utils.Utils;

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
        companyResponse.setId(company.getId());
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

    public static UserJson ReqJsonToUserJson(RequestJson requestJson){
            UserJson userJson = new UserJson();
            userJson.setActive(requestJson.isActive());
            userJson.setUserId(requestJson.getUserId());
            userJson.setUserName(requestJson.getUserName());
            userJson.setCreationDate(Utils.ldparse(requestJson.getCreationDate()));
            userJson.setEmailVerified(requestJson.isEmailVerified());
            userJson.setPassword(requestJson.getPassword());
            userJson.setStatus(requestJson.getStatus());
            userJson.setRole(requestJson.getRole());
            userJson.setEmployee(ReqJsonToEmployeeJson(requestJson));
            return userJson;
    }

    public static EmployeeJson ReqJsonToEmployeeJson(RequestJson requestJson){
        EmployeeJson employeeJson = new EmployeeJson();
        employeeJson.setEmployeeId(requestJson.getEmployeeId());
        employeeJson.setInternalEmployeeId(requestJson.getInternalEmployeeId());
        employeeJson.setEmail(requestJson.getEmail());
        employeeJson.setAssignedEndTime(Utils.ltparse(requestJson.getAssignedStartTime()));
        employeeJson.setAssignedEndTime(Utils.ltparse(requestJson.getAssignedEndTime()));
        employeeJson.setFirstName(requestJson.getFirstName());
        employeeJson.setLastName(requestJson.getLastName());
        employeeJson.setGroupId(requestJson.getGroupId());
        employeeJson.setOrganizationId(requestJson.getOrganizationId());
        employeeJson.setPositionId(requestJson.getPositionId());
        employeeJson.setCompanyId(requestJson.getCompanyId());
        return employeeJson;
    }

}
