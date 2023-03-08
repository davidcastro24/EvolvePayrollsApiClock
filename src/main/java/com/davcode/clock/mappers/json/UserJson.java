package com.davcode.clock.mappers.json;

import com.davcode.clock.models.Employee;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.time.LocalDate;

@Data
public class UserJson {
    private Long userId;
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
    private EmployeeJson employee;
    private boolean autoScheduleAllowed;
}
