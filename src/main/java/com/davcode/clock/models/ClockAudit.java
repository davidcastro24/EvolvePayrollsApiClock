package com.davcode.clock.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Getter
@Setter
public class ClockAudit {
    @Id
    @GeneratedValue
    @Column(unique = true, nullable = false)
    private Long id;
    private LocalTime startTime;
    private LocalTime endTime;
    private boolean accepted;
    private boolean rejected;
    private LocalDate submitDate;
    private LocalDate authorizationDate;
    private String authUserName;
    @OneToOne
    @JoinColumn(name = "audit_clock_id")
    private Clock clock;

}
