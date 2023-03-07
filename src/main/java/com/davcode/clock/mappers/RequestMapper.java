package com.davcode.clock.mappers;

import com.davcode.clock.mappers.dto.RequestDTO;
import com.davcode.clock.mappers.json.EmployeeJson;
import com.davcode.clock.mappers.json.UserJson;
import com.davcode.clock.models.Employee;
import com.davcode.clock.models.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface RequestMapper {
    RequestMapper INSTANCE = Mappers.getMapper(RequestMapper.class);

    @Mapping(source = "user", target = "user")
    @Mapping(source = "employee", target = "employee")
    RequestDTO toRequestDto(RequestJson requestJson);

    User toUser(UserJson user);
    Employee toEmployee(EmployeeJson employee);
}
