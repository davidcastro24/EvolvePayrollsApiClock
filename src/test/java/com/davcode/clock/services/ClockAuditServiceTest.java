package com.davcode.clock.services;

import com.davcode.clock.models.Clock;
import com.davcode.clock.models.ClockAudit;
import com.davcode.clock.models.Employee;
import com.davcode.clock.models.User;
import com.davcode.clock.repositories.ClockAuditRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class ClockAuditServiceTest {

    @InjectMocks
    private ClockAuditService clockAuditService;

    @Mock
    private ClockService clockService;

    @Mock
    private ClockAuditRepository clockAuditRepository;

    private List<Clock> clocks;
    private List<ClockAudit> clockAudits;
    private Long cnt = 1L;
    private User user;
    private Clock clock;
    private ClockAudit clockAudit;

    @BeforeEach
    public void setup(){
        clocks = new ArrayList<>();
        clockAudits = new ArrayList<>();
        user = createUser();
        clock = createClock(user,true);
        clockAudit = createClockAudit();

        clockAudits.add(clockAudit);
        clockAudits.add(createClockAudit());
        clockAudits.add(createClockAudit());
        clockAudits.add(createClockAudit());
        clockAudits.add(createClockAudit());

        clocks.add(clock);
        clocks.add(createClock(user,true));
        clocks.add(createClock(user,true));
        clocks.add(createClock(user,true));
        clocks.add(createClock(user,true));
    }

    @Test
    public void testAddClockAudit(){
        clockAuditService.addClockAudit(clockAudit);

        verify(clockAuditRepository,times(1)).save(any(ClockAudit.class));
    }

    @Test
    public void testGetClockAuditById(){
        when(clockAuditRepository.findById(1L)).thenReturn(Optional.of(clockAudit));

        ClockAudit result = clockAuditService.getClockAuditById(1L);

        verify(clockAuditRepository,times(1)).findById(anyLong());
        assertEquals(clockAudit,result);
    }

    @Test
    public void testGetAll(){
        when(clockAuditRepository.findAll()).thenReturn(clockAudits);

        List<ClockAudit> result = clockAuditService.getAll();

        verify(clockAuditRepository,times(1)).findAll();
        assertEquals(clockAudits.size(),result.size());
    }

    @Test
    public void testGetAllByUser(){
        when(clockAuditRepository.findAllByClock_User_Id(1L)).thenReturn(clockAudits);

        List<ClockAudit> result = clockAuditService.getAllByUser(1L);

        verify(clockAuditRepository,times(1)).findAllByClock_User_Id(anyLong());
        assertEquals(clockAudits.size(),result.size());
    }

    @Test
    public void testGetAllByAuthUser(){
        when(clockAuditRepository.findClockAuditByAuthUserName("test")).thenReturn(clockAudits);

        List<ClockAudit> result = clockAuditService.getAllByAuthUser("test");

        verify(clockAuditRepository,times(1)).findClockAuditByAuthUserName(anyString());
        assertEquals(clockAudits.size(),result.size());
    }

    @Test
    public void testAuthorizeRequest(){
        when(clockAuditRepository.findById(1L)).thenReturn(Optional.of(clockAudit));

        clockAuditService.authorizeRequest(1L,"test");

        verify(clockAuditRepository,times(1)).findById(anyLong());
        verify(clockAuditRepository,times(1)).save(any(ClockAudit.class));
        verify(clockService,times(1)).updateTime(anyLong(),any(LocalTime.class),any(LocalTime.class));

        assertEquals(true,clockAudit.isAccepted());
        assertEquals(false,clockAudit.isRejected());

    }

    @Test
    public void testDenyRequest(){
        when(clockAuditRepository.findById(1L)).thenReturn(Optional.of(clockAudit));

        clockAuditService.denyRequest(1L,"test");

        verify(clockAuditRepository,times(1)).findById(anyLong());
        verify(clockAuditRepository,times(1)).save(any(ClockAudit.class));
        verify(clockService,times(1)).setUnderReview(anyLong(),eq(false));

        assertEquals(false,clockAudit.isAccepted());
        assertEquals(true,clockAudit.isRejected());
    }

    @Test
    public void testDeleteClockAudit(){
        clockAuditService.deleteClockAudit(1L);

        verify(clockAuditRepository,times(1)).deleteById(anyLong());
    }

    @Test
    public void testSubmitRequest(){
        when(clockService.getClock(1L)).thenReturn(clock);

        clockAuditService.submitRequest(1L,LocalTime.parse("08:00"),LocalTime.NOON);

        verify(clockService,times(1)).getClock(anyLong());
        verify(clockAuditRepository,times(1)).save(any(ClockAudit.class));
        verify(clockService,times(1)).setUnderReview(anyLong(),eq(true));
    }

    @Test
    public void testGetAllByCompany(){
        when(clockAuditRepository.findAllByCompanyId(1L)).thenReturn(clockAudits);

        List<ClockAudit> result = clockAuditService.getAllByCompany(1L);

        verify(clockAuditRepository,times(1)).findAllByCompanyId(anyLong());

        assertEquals(clockAudits.get(0),result.get(0));
    }

    public Clock createClock(User user, boolean active){
        Clock clock = new Clock();
        clock.setClockId(1L);
        clock.setStartTime(LocalTime.parse("08:00"));
        clock.setEndTime(LocalTime.NOON);
        clock.setActiveFlag(active);
        clock.setUnderReview(false);
        clock.setUser(user);
        cnt++;
        return clock;
    }

    public User createUser(){
        User user = new User();
        user.setUserName("test"+cnt);
        user.setId(cnt);
        user.setEmployee(createEmployee());
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
        return employee;
    }

    public ClockAudit createClockAudit(){
        ClockAudit clockAudit = new ClockAudit();
        clockAudit.setClock(clock);
        clockAudit.setId(cnt);
        clockAudit.setAccepted(false);
        clockAudit.setRejected(false);
        clockAudit.setSubmitDate(LocalDate.now());
        clockAudit.setStartTime(LocalTime.parse("07:00"));
        clockAudit.setEndTime(LocalTime.parse("16:00"));
        clockAudit.setCompanyId(cnt);
        return clockAudit;
    }
}
