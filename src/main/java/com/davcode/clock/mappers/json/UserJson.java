package com.davcode.clock.mappers.json;

import com.davcode.clock.models.Employee;
import lombok.Data;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import java.time.LocalDate;
import java.util.List;

@Data
public class UserJson {
    private Long userId;
    private String userName;
    private String password;
    private List<String> roles;
    private boolean isActive;
    private LocalDate creationDate;
    private boolean changePasswordOnNextLogin;
    private LocalDate suspensionDate;
    private boolean emailVerified;
    private char status;
    private LocalDate lastLogin;
    private EmployeeJson employee;
    private boolean autoScheduleAllowed;
    private boolean credentialsExpired;
}
