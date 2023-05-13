package com.davcode.clock.services;

import com.davcode.clock.mappers.dto.DtoMapper;
import com.davcode.clock.mappers.dto.EmployeeResponse;
import com.davcode.clock.mappers.json.EmployeeJson;
import com.davcode.clock.models.Company;
import com.davcode.clock.models.Employee;
import com.davcode.clock.repositories.EmployeeRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.TimeZone;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

    @InjectMocks
    private EmployeeService employeeService;

    @Mock
    private CompanyService companyService;

    @Mock
    private EmployeeRepository employeeRepository;

    private static Company company;
    private static EmployeeResponse employeeResponse;
    private static EmployeeJson employeeJson;
    private static Employee employee;
    private static Long empCnt = 1L;
    private static List<Employee> employees = new ArrayList<>();

    @BeforeAll
    public static void setup(){
        company = createCompany(empCnt);
        employee = createEmployee(company);
        employeeResponse = DtoMapper.employeeToDto(employee);
        employeeJson = createEmployeeJson();
        employees.add(employee);
        for (int i = 0; i < 5; i++)
            employees.add(createEmployee(company));

    }

    @Test
    public void testAddEmployee(){
        when(companyService.getById(1L)).thenReturn(company);

        employeeService.addEmployee(employeeJson);

        verify(companyService,times(1)).getById(1L);
        verify(employeeRepository,times(1)).save(any(Employee.class));
    }

    @Test
    public void testAddEmployeeSimple(){
        employeeService.addEmployee(employee);

        verify(employeeRepository,times(1)).save(any(Employee.class));
    }

    @Test
    public void testGetEmployee(){
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));

        employeeService.getEmployee(1L);

        verify(employeeRepository,times(1)).findById(1L);
    }

    @Test
    public void testUpdateEmployee(){
        Employee updated = employee;
        updated.setFirstName("TestUpdate");

        employeeService.updateEmployee(1L,updated);

        verify(employeeRepository,times(1)).save(any(Employee.class));
    }

    @Test
    public void testDeleteEmployee(){
       employeeService.deleteEmployee(1L);

        verify(employeeRepository,times(1)).deleteById(anyLong());
    }

    @Test
    public void testGetAllEmployeesFromCompany(){
        when(employeeRepository.findAllByCompany_Id(1L)).thenReturn(employees);

        List<EmployeeResponse> result = employeeService.getAllEmployeesFromCompany(1L);

        assertEquals(employees.size(),result.size());
        assertEquals(employeeResponse,result.get(0));

        verify(employeeRepository,times(1)).findAllByCompany_Id(anyLong());
    }

    @Test
    public void testCalculateHourlySalary(){
        Double result = employeeService.calculateHourlySalary(employee);
        assertEquals(62.5,result);
    }

    private static Employee createEmployee(Company company){
        Employee employee = new Employee();
        employee.setId(empCnt);
        employee.setFirstName("Test");
        employee.setLastName("Test");
        employee.setAssignedStartTime(LocalTime.parse("08:00"));
        employee.setAssignedEndTime(LocalTime.NOON);
        employee.setCompany(company);
        employee.setHourlySalary(500L);
        employee.setMonthlySalary(5000L);
        employee.setEmail("email@email.com");
        empCnt++;
        return employee;
    }

    private static Company createCompany(Long id){
        Company company = new Company();
        company.setId(id);
        company.setCompanyName("Test");
        company.setTimeZone(TimeZone.getDefault());
        company.setActive(true);
        return company;
    }

    private static EmployeeJson createEmployeeJson() {
        return EmployeeJson.builder()
                .employeeId(1L)
                .firstName("Test")
                .lastName("Test")
                .assignedStartTime("08:00")
                .assignedEndTime("12:00")
                .companyId(1L)
                .hourlySalary(500L)
                .monthlySalary(5000L)
                .email("Email@Email.Com")
                .build();
    }

}
