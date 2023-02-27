package com.davcode.clock.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Setter
@Getter
public class Clock {
    @Id
    @GeneratedValue
    @Column(name = "clock_id", nullable = false)
    private Long clockId;
    private LocalDate activeDate;
    private LocalTime startTime;
    private LocalTime endTime;
    private boolean activeFlag;
    private boolean underReview;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}