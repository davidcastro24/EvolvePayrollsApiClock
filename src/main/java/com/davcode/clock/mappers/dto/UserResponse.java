package com.davcode.clock.mappers.dto;

import lombok.Data;

@Data
public class UserResponse {
    private Long id;
    private String userName;
    private String role;
    private Long employeeId;
}