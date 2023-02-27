package com.davcode.clockapp.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Company {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    private String companyName;
    private boolean emailVerificationRequired;

}
