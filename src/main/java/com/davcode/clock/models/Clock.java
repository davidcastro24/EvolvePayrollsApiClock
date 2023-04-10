package com.davcode.clock.models;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Setter
@Getter
@NoArgsConstructor
@RequiredArgsConstructor
@ToString
public class Clock {
    @Id
    @GeneratedValue
    @Column(unique = true, nullable = false)
    private Long clockId;

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