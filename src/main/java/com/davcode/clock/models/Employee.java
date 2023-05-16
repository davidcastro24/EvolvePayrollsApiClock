package com.davcode.clock.models;

import lombok.*;

import jakarta.persistence.*;
import java.time.LocalTime;


@Entity
@Table(name="Employee")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Builder(toBuilder = true)
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;
    @NonNull
    private String firstName;
    @NonNull
    private String lastName;
    @NonNull
    private String email;
    @Column(unique = true)
    private Long internalEmployeeId;
    private Integer organizationId;
    private Integer groupId;
    private Integer positionId;
    @NonNull
    private LocalTime assignedStartTime;
    @NonNull
    private LocalTime assignedEndTime;
    @NonNull
    private Long monthlySalary;
    private Long hourlySalary;
    @NonNull
    private String contractType;
    private boolean emailConfirmationRequired;
    private boolean autoScheduleAllowed;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "company_id")
    private Company company;

}