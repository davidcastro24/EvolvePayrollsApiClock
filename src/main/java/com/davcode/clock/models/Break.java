package com.davcode.clock.models;

import lombok.*;

import jakarta.persistence.*;
import java.time.LocalTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class Break {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    private LocalTime startTime;
    private LocalTime endTime;
    @NonNull
    private boolean active;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "clock_clock_id")
    private Clock clock;
}
