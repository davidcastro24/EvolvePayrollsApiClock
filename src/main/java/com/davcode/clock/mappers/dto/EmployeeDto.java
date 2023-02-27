package com.davcode.clock.mappers.dto;

import lombok.Data;

@Data
public class EmployeeDto {
    private Long id;
    private String name;
    private String email;
    private Long internalId;
}
