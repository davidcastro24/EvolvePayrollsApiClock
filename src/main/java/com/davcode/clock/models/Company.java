package com.davcode.clock.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.TimeZone;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Company {
    @Id
    @GeneratedValue
    @Column(nullable = false, unique = true)
    private Long id;
    private String companyName;
    private TimeZone timeZone;
    private boolean emailVerificationRequired;
    private boolean isActive;
    private boolean allowAutoSchedule;

}