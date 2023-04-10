package com.davcode.clock.services;

import com.davcode.clock.exceptions.Exceptions;
import com.davcode.clock.models.Company;
import com.davcode.clock.models.Schedule;
import com.davcode.clock.repositories.ScheduleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.TimeZone;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class ScheduleServiceTest {

    @InjectMocks
    private ScheduleService scheduleService;

    @Mock
    private ScheduleRepository scheduleRepository;

    private List<Schedule> schedules;
    private Long cnt = 1L;
    private Company company;
    private Schedule schedule;

    @BeforeEach
    public void setup(){
        company = createCompany();
        schedule = createSchedule(true, LocalTime.MIN, LocalTime.NOON);
        schedules = new ArrayList<>();

        //schedules.add(schedule);
        schedules.add(createSchedule(false, LocalTime.NOON,LocalTime.parse("16:00")));
        schedules.add(createSchedule(false, LocalTime.parse("16:00"),LocalTime.parse("20:00")));
        schedules.add(createSchedule(true, LocalTime.parse("20:00"),LocalTime.parse("23:59")));
    }

    @Test
    public void testAddSchedule(){
        when(scheduleRepository.findAllByCompany_Id(1L)).thenReturn(schedules);

        scheduleService.addSchedule(schedule);

        verify(scheduleRepository,times(1)).findAllByCompany_Id(anyLong());
    }

    @Test
    public void testAddSchedule_Conflicted(){
        schedules.add(schedule);
        when(scheduleRepository.findAllByCompany_Id(1L)).thenReturn(schedules);

        assertThrows(Exceptions.ConflictedScheduleException.class, () -> scheduleService.addSchedule(schedule));
    }

    @Test
    public void testGetSchedule(){
        when(scheduleRepository.findById(1L)).thenReturn(Optional.of(schedule));

        Schedule result = scheduleService.getSchedule(1L);

        verify(scheduleRepository,times(1)).findById(anyLong());

        assertEquals(schedule,result);
    }

    @Test
    public void testDeleteSchedule(){
        scheduleService.deleteSchedule(1L);

        verify(scheduleRepository,times(1)).deleteById(anyLong());
    }

    @Test
    public void testGetAllFromCompany(){
        when(scheduleRepository.findAllByCompany_Id(1L)).thenReturn(schedules);
        List<Schedule> result = scheduleService.getAllFromCompany(1L);

        verify(scheduleRepository,times(1)).findAllByCompany_Id(anyLong());
        assertEquals(schedules.get(0),result.get(0));
    }


    private Schedule createSchedule(boolean overtime, LocalTime start, LocalTime end){
        Schedule schedule = new Schedule();
        schedule.setId(cnt);
        schedule.setOvertime(overtime);
        schedule.setRate(15.34);
        schedule.setStartTime(start);
        schedule.setEndTime(end);
        schedule.setCompany(company);
        cnt++;
        return schedule;
    }

    private static Company createCompany(){
        Company comp = new Company();
        comp.setId(1L);
        comp.setTimeZone(TimeZone.getDefault());
        comp.setActive(true);
        comp.setCompanyName("Test");
        return comp;
    }
}
