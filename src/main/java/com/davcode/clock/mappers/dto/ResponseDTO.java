package com.davcode.clock.mappers.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ResponseDTO {
    private UserResponse userResponse;
    private EmployeeResponse employeeResponse;
}
