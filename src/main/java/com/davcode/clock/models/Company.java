package com.davcode.clock.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.TimeZone;

@Entity
@Setter
@Getter
public class Company {
    @Id
    @GeneratedValue
    @Column(name = "company_id", nullable = false, unique = true)
    private Long companyId;
    private String companyName;
    private TimeZone timeZone;
    private boolean emailVerificationRequired;
    private boolean isActive;

}