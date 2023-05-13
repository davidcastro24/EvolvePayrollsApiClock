package com.davcode.clock.services;

import com.davcode.clock.exceptions.Exceptions;
import com.davcode.clock.mappers.dto.ClockResponse;
import com.davcode.clock.mappers.dto.DtoMapper;
import com.davcode.clock.models.Clock;
import com.davcode.clock.models.Employee;
import com.davcode.clock.models.User;
import com.davcode.clock.repositories.ClockRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ClockServiceTest {

    @InjectMocks
    private ClockService clockService;

    @Mock
    private ClockRepository clockRepository;

    @Mock
    private UserService userService;

    private Clock clock;
    private User user;
    private List<Clock> clocks;
    private List<User> users;
    private Long cnt = 1L;
    private ClockResponse clockResponse;

    @BeforeEach
    public void setup(){
        user = createUser();
        clock = createClock(user,true);
        clockResponse = DtoMapper.clockToDto(clock);

        clocks = new ArrayList<>();
        users = new ArrayList<>();
        clocks.add(clock);
        users.add(user);
        clocks.add(createClock(user,true));
        users.add(createUser());
        clocks.add(createClock(user,false));
        users.add(createUser());
        clocks.add(createClock(user,true));
        users.add(createUser());
        clocks.add(createClock(user,false));
        users.add(createUser());
    }

    @Test
    public void testAddClockWithAutoSchedule(){
        user.setAutoScheduleAllowed(true);
        when(userService.getUserByIdInternal(1L)).thenReturn(user);
        when(clockRepository.findClockByUserId(1L)).thenReturn(clocks);

        clockService.addClock(1L);

        verify(clockRepository,times(1)).findClockByUserId(anyLong());
        verify(clockRepository,times(4)).save(any(Clock.class));
    }

    @Test
    public void testGetClock(){
        when(clockRepository.findById(1L)).thenReturn(Optional.of(clock));

        Clock result = clockService.getClock(1L);

        verify(clockRepository,times(1)).findById(anyLong());
        assertEquals(clock,result);
    }

    @Test
    public void testGetClock_throwError(){
        when(clockRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(Exceptions.ClockNotFoundException.class, () -> clockService.getClock(1L));

        verify(clockRepository,times(1)).findById(anyLong());
    }

    @Test
    public void testGetAll(){
        when(clockRepository.findAll()).thenReturn(clocks);

        List<ClockResponse> result = clockService.getAll();

        verify(clockRepository,times(1)).findAll();
        assertEquals(clockResponse,result.get(0));
    }

    @Test
    public void testGetAllByUserId(){
        when(clockRepository.findClockByUserId(1L)).thenReturn(clocks);

        List<ClockResponse> result = clockService.getAllByUserId(1L);

        verify(clockRepository,times(1)).findClockByUserId(anyLong());
        assertEquals(clockResponse,result.get(0));
    }

    @Test
    public void testGetAllByUserId_throwError(){
        when(clockRepository.findClockByUserId(1L)).thenReturn(Collections.emptyList());

        assertThrows(Exceptions.NoClocksException.class, () -> clockService.getAllByUserId(1L));
    }

    @Test
    public void testDeactivateAllActiveClocksFromUser(){
        when(clockRepository.findClockByUserId(1L)).thenReturn(clocks);

        clockService.deactivateAllActiveClocksFromUser(user);

        verify(clockRepository,times(1)).findClockByUserId(anyLong());
        verify(clockRepository,times(3)).save(any(Clock.class));

        for (Clock c : clocks)
            assertEquals(false,c.isActiveFlag());
    }

    @Test
    public void testGetCurrentClock(){
        when(clockRepository.findClockByUserIdAndActiveFlagAndActiveDate(1L,true, LocalDate.now()))
                .thenReturn(clock);

        ClockResponse result = clockService.getCurrentClock(1L);

        verify(clockRepository,times(1)).findClockByUserIdAndActiveFlagAndActiveDate(anyLong(),eq(true),any(LocalDate.class));
        assertEquals(clockResponse,result);
    }

    @Test
    public void testGetAllActiveClocksFromCompany(){
        when(userService.getUsersFromCompany(1L)).thenReturn(users);
        when(clockRepository.findByUserIdInAndActiveFlag(users.stream()
                .map(User::getId)
                .collect(Collectors.toList()),
                true)).thenReturn(clocks);

        clockService.getAllActiveClocksFromCompany(1L);

        verify(userService,times(1)).getUsersFromCompany(anyLong());
        verify(clockRepository,times(1)).findByUserIdInAndActiveFlag(anyList(),eq(true));
    }

    @Test
    public void testDeleteClock(){
        clockService.deleteClock(1L);

        verify(clockRepository,times(1)).deleteById(anyLong());
    }

    @Test
    public void testCheckout(){
        clockService.checkOut(clock);

        verify(clockRepository,times(1)).save(any(Clock.class));
        assertEquals(false,clock.isActiveFlag());
    }

    @Test
    public void testUpdateTime(){
        LocalTime startTime = LocalTime.parse("07:00");
        LocalTime endTime = LocalTime.parse("13:00");
        clock.setStartTime(startTime);
        clock.setEndTime(endTime);
        when(clockRepository.findById(1L)).thenReturn(Optional.of(clock));

        clockService.updateTime(1L,startTime,endTime);

        verify(clockRepository,times(1)).findById(anyLong());
        verify(clockRepository,times(1)).save(any(Clock.class));

        assertEquals(startTime,clock.getStartTime());
        assertEquals(endTime,clock.getEndTime());
    }

    @Test
    public void testSetUnderReview(){
        when(clockRepository.findById(1L)).thenReturn(Optional.of(clock));

        clockService.setUnderReview(1L,true);

        verify(clockRepository,times(1)).save(any(Clock.class));
        assertEquals(true,clock.isUnderReview());
    }


    public Clock createClock(User user, boolean active){
        Clock clock = new Clock();
        clock.setId(1L);
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
}
