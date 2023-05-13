package com.davcode.clock.mappers.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EmployeeResponse {
    private Long id;
    private String name;
    private String email;
    private Long internalId;
    private Long companyId;
    private String companyName;
    private Long monthlySalary;
    private Long hourlySalary;
    private String contractType;
}
