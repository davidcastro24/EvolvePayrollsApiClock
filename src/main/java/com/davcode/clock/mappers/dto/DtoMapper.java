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

    public static Employee employeeJsonToObj(EmployeeJson employeeJson){
        Employee employee = new Employee();
        employee.setId(employeeJson.getEmployeeId());
        employee.setFirstName(employeeJson.getFirstName());
        employee.setLastName(employeeJson.getLastName());
        employee.setInternalEmployeeId(employeeJson.getInternalEmployeeId());
        employee.setEmail(employeeJson.getEmail());
        employee.setPositionId(employeeJson.getPositionId());
        employee.setOrganizationId(employeeJson.getOrganizationId());
        employee.setAssignedStartTime(employeeJson.getAssignedStartTime());
        employee.setAssignedEndTime(employeeJson.getAssignedEndTime());
        employee.setGroupId(employeeJson.getGroupId());
        employee.setHourlySalary(employeeJson.getHourlySalary());
        employee.setMonthlySalary(employeeJson.getMonthlySalary());
        //employee.setCompany(employeeJson.getCompanyId());
        return employee;
    }

    public static Employee addCompanyToExistingEmployee(Employee employee, Company company){
        employee.setCompany(company);
        return employee;
    }

    public static EmployeeResponse employeeToDto(Employee employee){
        EmployeeResponse employeeResponse = new EmployeeResponse();
        employeeResponse.setId(employee.getId());
        employeeResponse.setEmail(employee.getEmail());
        employeeResponse.setInternalId(employee.getInternalEmployeeId());
        employeeResponse.setName(employee.getFirstName() + " " + employee.getLastName());
        employeeResponse.setCompanyId(employee.getCompany().getId());
        employeeResponse.setCompanyName(employee.getCompany().getCompanyName());
        employeeResponse.setMonthlySalary(employee.getMonthlySalary());
        employeeResponse.setHourlySalary(employee.getHourlySalary());
        employeeResponse.setContractType(employee.getContractType());

        return employeeResponse;
    }

    public static UserResponse UserToDto(User user){
        UserResponse userResponse = new UserResponse();
        userResponse.setUserName(user.getUserName());
        userResponse.setId(user.getId());
        userResponse.setRoles(user.getRoles());
        userResponse.setActive(user.isActive());
        userResponse.setCreationDate(user.getCreationDate());
        userResponse.setChangePasswordOnNextLogin(user.isChangePasswordOnNextLogin());
        userResponse.setSuspensionDate(user.getSuspensionDate());
        userResponse.setEmailVerified(user.isEmailVerified());
        userResponse.setStatus(user.getStatus());
        userResponse.setAutoScheduleAllowed(user.isAutoScheduleAllowed());
        userResponse.setLastLogin(user.getLastLogin());
        userResponse.setCredentialsExpired(user.isCredentialsExpired());
        if (user.getEmployee() != null)
            userResponse.setEmployeeId(user.getEmployee().getId());
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
            userJson.setRoles(requestJson.getRoles());
            userJson.setEmployee(ReqJsonToEmployeeJson(requestJson));
            userJson.setChangePasswordOnNextLogin(requestJson.isChangePasswordOnNextLogin());
            userJson.setCredentialsExpired(requestJson.isCredentialsExpired());
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
        employeeJson.setMonthlySalary(requestJson.getMonthlySalary());
        employeeJson.setHourlySalary(requestJson.getHourlySalary());
        employeeJson.setContractType(requestJson.getContractType());
        return employeeJson;
    }

}
