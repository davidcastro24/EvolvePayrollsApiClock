package com.davcode.clock.mappers;

import com.davcode.clock.mappers.json.UserJson;
import com.davcode.clock.models.Employee;
import com.davcode.clock.models.User;
import com.davcode.clock.utils.Utils;
import lombok.Data;

import java.util.List;

@Data
public class RequestJson {

    private Long userId;
    private String userName;
    private String password;
    private List<String> roles;
    private boolean isActive;
    private String creationDate;
    private boolean changePasswordOnNextLogin;
    private String suspensionDate;
    private boolean emailVerified;
    private char status;
    private String lastLogin;
    private boolean credentialsExpired;
    private boolean autoScheduleAllowed;

    private Long employeeId;
    private String firstName;
    private String lastName;
    private String email;
    private Long internalEmployeeId;
    private Integer organizationId;
    private Integer groupId;
    private Integer positionId;
    private String assignedStartTime;
    private String assignedEndTime;
    private Long monthlySalary;
    private Long hourlySalary;
    private String contractType;
    private Long companyId;


}
