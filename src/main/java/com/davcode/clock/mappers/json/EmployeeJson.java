package com.davcode.clock.mappers.json;

import lombok.*;

import java.time.LocalTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
