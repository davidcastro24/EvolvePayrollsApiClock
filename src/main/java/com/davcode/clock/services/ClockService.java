package com.davcode.clock.services;

import com.davcode.clock.exceptions.Exceptions;
import com.davcode.clock.mappers.dto.ClockResponse;
import com.davcode.clock.mappers.dto.DtoMapper;
import com.davcode.clock.models.Clock;
import com.davcode.clock.models.Employee;
import com.davcode.clock.models.User;
import com.davcode.clock.repositories.ClockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClockService {

    private final ClockRepository clockRepository;

    @Autowired
    private UserService userService;

    @Autowired
    public ClockService(ClockRepository clockRepository) {
        this.clockRepository = clockRepository;
    }

    public void addClock(Long userId){
        Clock clock = new Clock();
        clock.setStartTime(LocalTime.now());
        clock.setActiveFlag(true);
        clock.setActiveDate(LocalDate.now());
        clock.setUser(
                userService.getUserByIdInternal(userId)
        );

        clockRepository.save(clock);
    }

    public Clock getClock(Long id){
        Optional<Clock> clock = clockRepository.findById(id);
        if (clock.isPresent()){
            return clock.get();
        }
        throw new Exceptions.ClockNotFoundException("Clock not found");
    }

    public List<ClockResponse> getAll(){
        List<Clock> clocks = clockRepository.findAll();
        if (clocks.isEmpty())
            throw new Exceptions.NoClocksException("No Clocks");
        return clocks.stream().map(c -> DtoMapper.clockToDto(c)).collect(Collectors.toList());
    }

    public List<ClockResponse> getAllByUserId(Long userId){
        List<Clock> clocks = clockRepository.findClockByUserId(userId);
        if (clocks.isEmpty())
            throw new Exceptions.NoClocksException("No Clocks Inputted for this User");
        return clocks.stream().map(c -> DtoMapper.clockToDto(c)).collect(Collectors.toList());
    }

    public ClockResponse getCurrentClock(Long userId){
        Optional<Clock> currentClock = Optional.of(clockRepository.findClockByActivity(LocalDate.now(),true, userId));
        if (currentClock.isPresent())
            return DtoMapper.clockToDto(currentClock.get());
        throw new Exceptions.ClockNotFoundException("No active clocks");
    }

    public void updateTimePeriod(Clock clock){
        clock.setUnderReview(true);
        clockRepository.save(clock);
    }

    public void checkIn(Clock clock){
        clock.setActiveFlag(true);
        clock.setStartTime(LocalTime.now());
        clockRepository.save(clock);
    }

    public void checkOut(Clock clock){
        clock.setActiveFlag(false);
        clock.setEndTime(LocalTime.now());
        clockRepository.save(clock);
    }

    public ClockResponse automaticTimeSet(Long userId){
        User user = userService.getUserByIdInternal(userId);
        if (user.isAutoScheduleAllowed()){
            Employee employee = user.getEmployee();
            Clock clock = new Clock();
            clock.setUser(user);
            clock.setStartTime(employee.getAssignedStartTime());
            clock.setEndTime(employee.getAssignedEndTime());
            clock.setActiveDate(LocalDate.now());
            clock.setActiveFlag(true);
            clockRepository.save(clock);
            return DtoMapper.clockToDto(clock);
        }
        throw new Exceptions.NoAutomaticSchedulingForUser("User has no scheduling available");
    }

}
