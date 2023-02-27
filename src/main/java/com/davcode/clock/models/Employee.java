package com.davcode.clock.models;

import lombok.Data;
import javax.persistence.*;
import java.time.LocalTime;


@Entity
@Table(name="Employee")
@Data
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id", nullable = false)
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

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

}