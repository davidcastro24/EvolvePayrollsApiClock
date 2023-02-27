package com.davcode.clock.mappers.dto;

import com.davcode.clock.models.Employee;
import com.davcode.clock.models.User;
import lombok.Data;


@Data
public class RequestDTO {
    private User user;
    private Employee employee;

}
