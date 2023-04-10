package com.davcode.clock.services;

import com.davcode.clock.mappers.dto.CompanyResponse;
import com.davcode.clock.mappers.dto.DtoMapper;
import com.davcode.clock.models.Company;
import com.davcode.clock.models.Employee;
import com.davcode.clock.models.User;
import com.davcode.clock.repositories.CompanyRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;


import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.TimeZone;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CompanyServiceTest {

    @InjectMocks
    private CompanyService companyService;

    @Mock
    private CompanyRepository companyRepository;

    @Mock
    private UserService userService;

    private static Company company;
    private static CompanyResponse companyResponse;
    private static User user;

    private static List<Company> companies = new ArrayList<>();
    private static List<User> users = new ArrayList<>();

    private static Long companyCnt = 1L;
    private static Long userCnt = 1L;

    @BeforeAll
    public static void setup(){
        company = createTestCompany();
        companyResponse = DtoMapper.CompanyToDto(company);
        companies.add(company);
        companies.add(createTestCompany());
        companies.add(createTestCompany());
        user = createUser();
        users.add(user);
        users.add(createUser());
        users.add(createUser());
        users.add(createUser());
    }

    @Test
    public void testGetAll(){
        when(companyRepository.findAll()).thenReturn(companies);

        List<CompanyResponse> result = companyService.getAll();

        assertEquals(companies.size(),result.size());
        assertEquals(companyResponse,result.get(0));
        verify(companyRepository, times(1)).findAll();
    }

    @Test
    public void testGetById(){
        Long id = 1L;
        when(companyRepository.findById(id)).thenReturn(Optional.of(company));

        Company result = companyService.getById(id);

        assertEquals(company, result);
        verify(companyRepository,times(1)).findById(id);
    }

    @Test
    public void testPostCompany(){
        companyService.postCompany(company);

        verify(companyRepository,times(1)).save(company);

    }

    @Test
    public void testDeleteCompany(){
        Long id = 1L;
        companyService.deleteCompany(id);

        verify(companyRepository,times(1)).deleteById(id);
    }

    @Test
    public void testUpdateCompany(){
        Company updated = company;
        updated.setActive(false);
        companyService.updateCompany(updated);

        verify(companyRepository,times(1)).save(updated);
    }

    @Test
    public void testUpdateTimeZone(){
        Long id = 1L;
        String timeZoneString = "Pacific/Apia";
        TimeZone timeZone = TimeZone.getTimeZone(timeZoneString);
        when(companyRepository.findById(id)).thenReturn(Optional.of(company));

        companyService.updateTimeZone(id,timeZoneString);

        verify(companyRepository,times(1)).findById(id);
        verify(companyRepository,times(1)).save(company);

        assertEquals(timeZone,company.getTimeZone());
    }

    @Test
    public void testSetActivationStatus(){
        Long id = 1L;
        boolean newStatus = false;
        when(companyRepository.findById(id)).thenReturn(Optional.of(company));
        when(userService.getUsersFromCompany(id)).thenReturn(users);

        companyService.setActivationStatus(id,newStatus);

        verify(companyRepository,times(1)).findById(id);
        verify(userService,times(1)).getUsersFromCompany(id);
        verify(userService,times(users.size())).updateActiveFlag(anyLong(),eq(false));
        verify(companyRepository,times(1)).save(company);

        for (User user : users){
            assertEquals(newStatus,user.isActive());
        }
    }

    @Test
    public void testSetEmailConfirmation(){
        Long id = 1L;
        boolean emailConfirmation = true;
        when(companyRepository.findById(id)).thenReturn(Optional.of(company));
        when(userService.getUsersFromCompany(id)).thenReturn(users);

        companyService.setEmailConfirmation(id,emailConfirmation);

        verify(companyRepository,times(1)).findById(id);
        verify(userService,times(1)).getUsersFromCompany(id);
        verify(userService,times(users.size())).updateUser(any(User.class));
        verify(companyRepository,times(1)).save(company);

        for (User user : users){
            assertEquals(emailConfirmation,user.isEmailConfirmationRequired());
        }

    }

    @Test
    public void testSetAutoScheduleAllowed(){
        Long id = 1L;
        boolean autoSchedule = true;
        when(companyRepository.findById(id)).thenReturn(Optional.of(company));
        when(userService.getUsersFromCompany(id)).thenReturn(users);

        companyService.setAutoScheduleAllowed(id,autoSchedule);

        verify(companyRepository,times(1)).findById(id);
        verify(userService,times(1)).getUsersFromCompany(id);
        verify(userService,times(users.size())).updateUser(any(User.class));
        verify(companyRepository,times(1)).save(company);

        for (User user : users){
            assertEquals(autoSchedule,user.isAutoScheduleAllowed());
        }
    }



    private static Company createTestCompany(){
        Company comp = new Company();
        comp.setId(companyCnt);
        comp.setTimeZone(TimeZone.getDefault());
        comp.setActive(true);
        comp.setCompanyName("Test" + companyCnt);
        companyCnt++;
        return comp;
    }

    private static User createUser(){
        Employee employee = new Employee();
        employee.setId(userCnt);
        employee.setEmail("test@test.com");
        employee.setFirstName("test");
        employee.setLastName("test");
        employee.setCompany(company);

        User user = new User();
        user.setId(userCnt);
        user.setUserName("test"+userCnt);
        user.setPassword("test"+userCnt);
        user.setCreationDate(LocalDate.now());
        user.setStatus('A');
        user.setEmployee(employee);

        userCnt++;

        return user;

    }
}
