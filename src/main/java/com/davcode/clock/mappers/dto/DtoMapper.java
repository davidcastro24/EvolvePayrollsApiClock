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
        /*Employee employee = new Employee();
        employee.setId(employeeJson.getEmployeeId());
        employee.setFirstName(employeeJson.getFirstName());
        employee.setLastName(employeeJson.getLastName());
        employee.setInternalEmployeeId(employeeJson.getInternalEmployeeId());
        employee.setEmail(employeeJson.getEmail());
        employee.setPositionId(employeeJson.getPositionId());
        employee.setOrganizationId(employeeJson.getOrganizationId());
        employee.setAssignedStartTime(Utils.ltparse(employeeJson.getAssignedStartTime()));
        employee.setAssignedEndTime(Utils.ltparse(employeeJson.getAssignedEndTime()));
        employee.setGroupId(employeeJson.getGroupId());
        employee.setHourlySalary(employeeJson.getHourlySalary());
        employee.setMonthlySalary(employeeJson.getMonthlySalary());*/

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
                .build();
        //employee.setCompany(employeeJson.getCompanyId());
        //return employee;
    }

    public static Employee addCompanyToExistingEmployee(Employee employee, Company company){
        employee.setCompany(company);
        return employee;
    }

    public static EmployeeResponse employeeToDto(Employee employee){
        /*EmployeeResponse employeeResponse = new EmployeeResponse();
        employeeResponse.setId(employee.getId());
        employeeResponse.setEmail(employee.getEmail());
        employeeResponse.setInternalId(employee.getInternalEmployeeId());
        employeeResponse.setName(employee.getFirstName() + " " + employee.getLastName());
        employeeResponse.setCompanyId(employee.getCompany().getId());
        employeeResponse.setCompanyName(employee.getCompany().getCompanyName());
        employeeResponse.setMonthlySalary(employee.getMonthlySalary());
        employeeResponse.setHourlySalary(employee.getHourlySalary());
        employeeResponse.setContractType(employee.getContractType());*/

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
        /*UserResponse userResponse = new UserResponse();
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
        return userResponse;*/
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
        ClockResponse clockResponse = new ClockResponse();
        clockResponse.setClockId(clock.getId());
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
        /*EmployeeJson employeeJson = new EmployeeJson();
        employeeJson.setEmployeeId(requestJson.getEmployeeId());
        employeeJson.setInternalEmployeeId(requestJson.getInternalEmployeeId());
        employeeJson.setEmail(requestJson.getEmail());
        employeeJson.setAssignedEndTime(requestJson.getAssignedStartTime());
        employeeJson.setAssignedEndTime(requestJson.getAssignedEndTime());
        employeeJson.setFirstName(requestJson.getFirstName());
        employeeJson.setLastName(requestJson.getLastName());
        employeeJson.setGroupId(requestJson.getGroupId());
        employeeJson.setOrganizationId(requestJson.getOrganizationId());
        employeeJson.setPositionId(requestJson.getPositionId());
        employeeJson.setCompanyId(requestJson.getCompanyId());
        employeeJson.setMonthlySalary(requestJson.getMonthlySalary());
        employeeJson.setHourlySalary(requestJson.getHourlySalary());
        employeeJson.setContractType(requestJson.getContractType());
        return employeeJson;*/
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
