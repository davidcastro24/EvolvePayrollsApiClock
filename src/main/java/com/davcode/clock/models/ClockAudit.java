package com.davcode.clock.models;

import jakarta.persistence.*;
import lombok.*;



import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class ClockAudit {
    @Id
    @GeneratedValue
    @Column(unique = true, nullable = false)
    private Long id;
    @NonNull
    private LocalTime startTime;
    @NonNull
    private LocalTime endTime;
    @NonNull
    private boolean accepted;
    @NonNull
    private boolean rejected;
    @NonNull
    private LocalDate submitDate;
    private LocalDate authorizationDate;
    private String authUserName;
    @NonNull
    private Long companyId;
    @NonNull
    @OneToOne
    @JoinColumn(name = "audit_clock_id")
    private Clock clock;

}
