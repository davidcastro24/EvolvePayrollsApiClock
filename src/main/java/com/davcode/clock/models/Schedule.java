package com.davcode.clock.models;

import lombok.*;

import jakarta.persistence.*;
import java.time.LocalTime;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
public class Schedule {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    @NonNull
    private LocalTime startTime;
    @NonNull
    private LocalTime endTime;
    @NonNull
    private double rate;
    @NonNull
    private boolean isOvertime;
    @NonNull
    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

}
