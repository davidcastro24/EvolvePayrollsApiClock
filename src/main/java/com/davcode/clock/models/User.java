package com.davcode.clock.models;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "User")
@Data
public class User {
    @Id
    @Column(name = "user_id", nullable = false, unique = true)
    private Long userId;
    @Column(unique = true)
    private String userName;
    private String password;
    private String role;
    private boolean isActive;
    private LocalDate creationDate;
    private boolean changePasswordOnNextLogin;
    private LocalDate suspensionDate;
    private boolean emailVerified;
    private char status;
    private LocalDate lastLogin;
    @OneToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;
}