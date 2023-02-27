package com.davcode.clockapp.models;

import lombok.Data;
import javax.persistence.*;
import java.time.LocalTime;


@Entity
@Table(name="Employee")
@Data
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String firstName;
    private String lastName;
    private Long employeeId;
    private Integer organizationId;
    private Integer groupId;
    private Integer positionId;
    private LocalTime assignedStartTime;
    private LocalTime assignedEndTime;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;


}