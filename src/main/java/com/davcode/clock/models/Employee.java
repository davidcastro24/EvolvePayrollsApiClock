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
    @Column(unique = true, nullable = false)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private Long internalEmployeeId;
    private Integer organizationId;
    private Integer groupId;
    private Integer positionId;
    private LocalTime assignedStartTime;
    private LocalTime assignedEndTime;
    private Long monthlySalary;
    private Long hourlySalary;
    private String contractType;
    private boolean emailConfirmationRequired;
    private boolean autoScheduleAllowed;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "company_id")
    private Company company;

}