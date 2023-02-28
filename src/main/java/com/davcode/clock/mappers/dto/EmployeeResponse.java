package com.davcode.clock.mappers.dto;

import lombok.Data;

@Data
public class EmployeeResponse {
    private Long id;
    private String name;
    private String email;
    private Long internalId;
    private Long companyId;
}
