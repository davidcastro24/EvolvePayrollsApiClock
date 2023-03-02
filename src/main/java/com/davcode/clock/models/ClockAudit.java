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
    @Column(name = "clockaudit_id", nullable = false)
    private Long clockAuditId;
    private LocalTime startTime;
    private LocalTime endTime;
    private boolean accepted;
    private boolean rejected;
    private LocalDate submitDate;
    private LocalDate authorizationDate;
    private String authUserName;
    @OneToOne
    @JoinColumn(name = "audit_clock_id")
    private Clock auditClock;

}
