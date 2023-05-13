package com.davcode.clock.models;

import lombok.*;

import jakarta.persistence.*;
import java.util.TimeZone;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class Company {
    @Id
    @GeneratedValue
    @Column(nullable = false, unique = true)
    private Long id;
    @NonNull
    private String companyName;
    @NonNull
    private TimeZone timeZone;
    @NonNull
    private boolean emailVerificationRequired;
    @NonNull
    private boolean isActive;
    @NonNull
    private boolean allowAutoSchedule;

}