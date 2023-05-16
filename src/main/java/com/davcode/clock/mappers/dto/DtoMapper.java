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
         return Employee.builder()
                .id(employeeJson.getEmployeeId())
                .firstName(employeeJson.getFirstName())
                .lastName(employeeJson.getLastName())
                .internalEmployeeId(employeeJson.getInternalEmployeeId())
                .email(employeeJson.getEmail())
                .positionId(employeeJson.getPositionId())
                .organizationId(employeeJson.getOrganizationId())
                .assignedStartTime(Utils.ltparse(employeeJson.getAssignedStartTime()))
                .assignedEndTime(Utils.ltparse(employeeJson.getAssignedEndTime()))
                .groupId(employeeJson.getGroupId())
                .hourlySalary(employeeJson.getHourlySalary())
                .monthlySalary(employeeJson.getMonthlySalary())
                 .contractType(employeeJson.getContractType())
                .build();
    }

    public static Employee addCompanyToExistingEmployee(Employee employee, Company company){
        employee.setCompany(company);
        return employee;
    }

    public static EmployeeResponse employeeToDto(Employee employee){
        return EmployeeResponse.builder()
                .id(employee.getId())
                .email(employee.getEmail())
                .internalId(employee.getInternalEmployeeId())
                .name(employee.getFirstName() + " " + employee.getLastName())
                .companyId(employee.getCompany().getId())
                .companyName(employee.getCompany().getCompanyName())
                .monthlySalary(employee.getMonthlySalary())
                .hourlySalary(employee.getHourlySalary())
                .contractType(employee.getContractType())
                .build();

    }

    public static UserResponse UserToDto(User user){
        return UserResponse.builder()
                .userName(user.getUserName())
                .id(user.getId())
                .roles(user.getRoles())
                .isActive(user.isActive())
                .creationDate(user.getCreationDate())
                .changePasswordOnNextLogin(user.isChangePasswordOnNextLogin())
                .suspensionDate(user.getSuspensionDate())
                .emailVerified(user.isEmailVerified())
                .status(user.getStatus())
                .autoScheduleAllowed(user.isAutoScheduleAllowed())
                .lastLogin(user.getLastLogin())
                .credentialsExpired(user.isCredentialsExpired())
                .employeeId(user.getEmployee().getId() > 0 ? user.getEmployee().getId() : 0)
                .build();
    }

    public static CompanyResponse CompanyToDto(Company company){
        CompanyResponse companyResponse = new CompanyResponse();
        companyResponse.setCompanyName(company.getCompanyName());
        companyResponse.setId(company.getId());
        companyResponse.setTimeZone(company.getTimeZone());
        return companyResponse;
    }

    public static ClockResponse clockToDto(Clock clock){
        return ClockResponse.builder()
                .clockId(clock.getId())
                .activeDate(clock.getActiveDate())
                .activeFlag(clock.isActiveFlag())
                .endTime(clock.getEndTime())
                .startTime(clock.getStartTime())
                .userName(clock.getUser().getUserName())
                .underReview(clock.isUnderReview())
                .build();
    }

    public static UserJson ReqJsonToUserJson(RequestJson requestJson){
        return UserJson.builder()
                .userId(requestJson.getUserId())
                .isActive(requestJson.isActive())
                .userName(requestJson.getUserName())
                .creationDate(Utils.ldparse(requestJson.getCreationDate()))
                .emailVerified(requestJson.isEmailVerified())
                .password(requestJson.getPassword())
                .status(requestJson.getStatus())
                .roles(requestJson.getRoles())
                .employee(ReqJsonToEmployeeJson(requestJson))
                .changePasswordOnNextLogin(requestJson.isChangePasswordOnNextLogin())
                .credentialsExpired(requestJson.isCredentialsExpired())
                .build();
    }

    public static EmployeeJson ReqJsonToEmployeeJson(RequestJson requestJson){
        return EmployeeJson.builder()
                .employeeId(requestJson.getEmployeeId())
                .internalEmployeeId(requestJson.getInternalEmployeeId())
                .email(requestJson.getEmail())
                .assignedStartTime(requestJson.getAssignedStartTime())
                .assignedEndTime(requestJson.getAssignedEndTime())
                .firstName(requestJson.getFirstName())
                .lastName(requestJson.getLastName())
                .groupId(requestJson.getGroupId())
                .organizationId(requestJson.getOrganizationId())
                .companyId(requestJson.getCompanyId())
                .monthlySalary(requestJson.getMonthlySalary())
                .hourlySalary(requestJson.getHourlySalary())
                .contractType(requestJson.getContractType())
                .build();
    }

}
