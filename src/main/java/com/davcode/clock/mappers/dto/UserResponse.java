package com.davcode.clock.mappers.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class UserResponse {
    private Long id;
    private String userName;
    private List<String> roles;
    private boolean isActive;
    private LocalDate creationDate;
    private boolean changePasswordOnNextLogin;
    private LocalDate suspensionDate;
    private boolean emailVerified;
    private char status;
    private boolean autoScheduleAllowed;
    private LocalDate lastLogin;
    private boolean credentialsExpired;
    private Long employeeId;

}
