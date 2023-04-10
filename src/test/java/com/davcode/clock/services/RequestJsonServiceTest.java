package com.davcode.clock.services;

import com.davcode.clock.mappers.RequestJson;
import com.davcode.clock.mappers.RequestMapperImpl;
import com.davcode.clock.mappers.dto.RequestDTO;
import com.davcode.clock.models.Company;
import com.davcode.clock.models.Employee;
import com.davcode.clock.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalTime;
import java.util.TimeZone;

import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class RequestJsonServiceTest {

    @InjectMocks
    private RequestJsonService requestJsonService;

    @Mock
    private UserService userService;

    @Mock
    private EmployeeService employeeService;

    @Mock
    private RequestMapperImpl requestMapper;

    private RequestJson requestJson;
    private Long cnt = 1L;
    private RequestDTO requestDTO;

    @BeforeEach
    public void setup(){
        requestJson = createRequestJson();
        requestDTO = new RequestDTO(createUser(),createEmployee());
    }

    @Test
    public void testAddUserAndEmployee(){
        when(requestMapper.toRequestDto(requestJson)).thenReturn(requestDTO);
        requestJsonService.addUserAndEmployee(requestJson);

        verify(employeeService,times(1)).addEmployee(any(Employee.class));
        verify(userService,times(1)).addUser(any(User.class));
    }

    public RequestJson createRequestJson(){
        RequestJson requestJson = new RequestJson();
        requestJson.setActive(true);
        requestJson.setAssignedEndTime("11:00");
        requestJson.setAutoScheduleAllowed(true);
        requestJson.setAssignedStartTime("01:00");
        requestJson.setCompanyId(1L);
        requestJson.setChangePasswordOnNextLogin(true);
        requestJson.setContractType("test");
        requestJson.setCreationDate("20230101");
        requestJson.setChangePasswordOnNextLogin(true);
        requestJson.setEmail("test@test.com");
        requestJson.setCredentialsExpired(false);
        requestJson.setHourlySalary(15L);
        requestJson.setFirstName("test");
        requestJson.setLastName("test");
        requestJson.setEmployeeId(1L);
        requestJson.setUserName("tt");
        requestJson.setStatus('A');
        requestJson.setPositionId(1);
        requestJson.setPassword("tetete");
        requestJson.setMonthlySalary(1500L);

        return requestJson;
    }

    public User createUser(){
        User user = new User();
        user.setUserName("test"+cnt);
        user.setId(cnt);
        user.setEmployee(createEmployee());
        user.setAutoScheduleAllowed(true);
        return user;
    }

    public Employee createEmployee(){
        Employee employee = new Employee();
        employee.setFirstName("test");
        employee.setLastName("test");
        employee.setId(cnt);
        employee.setAssignedStartTime(LocalTime.parse("08:00"));
        employee.setAssignedEndTime(LocalTime.NOON);
        employee.setMonthlySalary(5100L);
        employee.setHourlySalary(500L);
        employee.setCompany(createCompany());
        return employee;
    }

    private Company createCompany(){
        Company comp = new Company();
        comp.setId(cnt);
        comp.setTimeZone(TimeZone.getDefault());
        comp.setActive(true);
        comp.setCompanyName("Test" + cnt);
        comp.setAllowAutoSchedule(true);
        return comp;
    }
}
