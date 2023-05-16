package com.davcode.clock.mappers.json;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
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
