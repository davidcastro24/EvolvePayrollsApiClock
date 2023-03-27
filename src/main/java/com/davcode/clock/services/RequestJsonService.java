package com.davcode.clock.services;

import com.davcode.clock.mappers.RequestJson;
import com.davcode.clock.mappers.RequestMapperImpl;
import com.davcode.clock.mappers.dto.RequestDTO;
import com.davcode.clock.models.Employee;
import com.davcode.clock.models.User;
import org.springframework.stereotype.Service;

@Service
public class RequestJsonService {

    private final UserService userService;
    private final EmployeeService employeeService;
    private final RequestMapperImpl requestMapper;

    public RequestJsonService(UserService userService, EmployeeService employeeService, RequestMapperImpl requestMapper) {
        this.userService = userService;
        this.employeeService = employeeService;
        this.requestMapper = requestMapper;
    }

    public void addUserAndEmployee(RequestJson requestJson){
        RequestDTO requestDTO = requestMapper.toRequestDto(requestJson);
        User user = requestDTO.getUser();
        Employee employee = requestDTO.getEmployee();

        employeeService.addEmployee(employee);
        user.setEmployee(employee);
        user.setAutoScheduleAllowed(employee.getCompany().isAllowAutoSchedule());
        userService.addUser(user);
    }
}
