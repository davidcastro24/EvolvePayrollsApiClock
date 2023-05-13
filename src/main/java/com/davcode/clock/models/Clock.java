package com.davcode.clock.models;

import lombok.*;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Setter
@Getter
@NoArgsConstructor
@RequiredArgsConstructor
public class Clock {
    @Id
    @GeneratedValue
    @Column(unique = true, nullable = false)
    private Long id;

    @NonNull
    private LocalDate activeDate;
    @NonNull
    private LocalTime startTime;
    @NonNull
    private LocalTime endTime;
    @NonNull
    private boolean activeFlag;
    private boolean underReview;
    @NonNull
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}