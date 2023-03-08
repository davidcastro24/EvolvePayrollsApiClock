package com.davcode.clock.mappers;

import com.davcode.clock.mappers.dto.DtoMapper;
import com.davcode.clock.mappers.dto.RequestDTO;
import com.davcode.clock.mappers.json.EmployeeJson;
import com.davcode.clock.mappers.json.UserJson;
import com.davcode.clock.models.Employee;
import com.davcode.clock.models.User;
import com.davcode.clock.services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RequestMapperImpl implements RequestMapper{

    private final CompanyService companyService;

    @Autowired
    public RequestMapperImpl(CompanyService companyService) {
        this.companyService = companyService;
    }

    @Override
    public RequestDTO toRequestDto(RequestJson requestJson) {
        RequestDTO requestDTO = new RequestDTO();
        requestDTO.setUser(toUser(DtoMapper.ReqJsonToUserJson(requestJson)));
        requestDTO.setEmployee(toEmployee(DtoMapper.ReqJsonToEmployeeJson(requestJson)));
        return requestDTO;
    }

    @Override
    public User toUser(UserJson userj) {
        User user = new User();
        user.setId(userj.getUserId());
        user.setUserName(userj.getUserName());
        user.setActive(userj.isActive());
        user.setPassword(userj.getPassword());
        user.setStatus(userj.getStatus());
        user.setEmailVerified(userj.isEmailVerified());
        user.setRole(userj.getRole());
        user.setCreationDate(userj.getCreationDate());
        user.setAutoScheduleAllowed(userj.isAutoScheduleAllowed());
        user.setEmployee(toEmployee(userj.getEmployee()));
        return user;
    }

    @Override
    public Employee toEmployee(EmployeeJson employeej) {
        Employee employee = new Employee();
        employee.setInternalEmployeeId(employeej.getInternalEmployeeId());
        employee.setEmail(employeej.getEmail());
        employee.setPositionId(employeej.getPositionId());
        employee.setFirstName(employeej.getFirstName());
        employee.setLastName(employeej.getLastName());
        employee.setAssignedStartTime(employeej.getAssignedStartTime());
        employee.setAssignedEndTime(employeej.getAssignedEndTime());
        employee.setCompany(companyService.getById(employeej.getCompanyId()));
        return employee;
    }
}
