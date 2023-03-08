package com.davcode.clock.mappers.json;

import lombok.Data;

import java.time.LocalTime;

@Data
public class EmployeeJson {
    private Long employeeId;
    private String firstName;
    private String lastName;
    private String email;
    private Long internalEmployeeId;
    private Integer organizationId;
    private Integer groupId;
    private Integer positionId;
    private LocalTime assignedStartTime;
    private LocalTime assignedEndTime;
    private Long companyId;
}
