package com.davcode.clock.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalTime;

@Entity
@Getter
@Setter
public class Schedule {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    private LocalTime startTime;
    private LocalTime endTime;
    private double rate;
    private boolean isOvertime;
    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

}
