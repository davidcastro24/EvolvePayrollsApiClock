package com.davcode.clock.mappers.dto;

import com.davcode.clock.models.Employee;
import com.davcode.clock.models.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequestDTO {
    private User user;
    private Employee employee;

}
