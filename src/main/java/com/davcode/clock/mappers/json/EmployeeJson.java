package com.davcode.clock.mappers.json;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
@Builder
public class EmployeeJson {
    private Long employeeId;
    private String firstName;
    private String lastName;
    private String email;
    private Long internalEmployeeId;
    private Integer organizationId;
    private Integer groupId;
    private Integer positionId;
    private String assignedStartTime;
    private String assignedEndTime;
    private Long companyId;
    private Long monthlySalary;
    private Long hourlySalary;
    private String contractType;
}
