package com.davcode.clock.services;

import com.davcode.clock.mappers.dto.ClockResponse;
import com.davcode.clock.mappers.dto.DtoMapper;
import com.davcode.clock.models.Clock;
import com.davcode.clock.repositories.ClockRepository;
import jdk.vm.ci.meta.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
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
                userService.getUser(userId)
        );

        clockRepository.save(clock);
    }

    public Clock getClock(Long id){
        return clockRepository.findById(id).get();
    }

    public List<Clock> getAll(){
        return clockRepository.findAll();
    }

    public List<ClockResponse> getAllByUserId(Long userId){
        List<Clock> clocks = clockRepository.findClockByUserId(userId);
        return clocks.stream().map(c -> DtoMapper.clockToDto(c)).collect(Collectors.toList());
    }

    public Clock getCurrentClock(Long userId){
        Optional<Clock> currentClock = Optional.of(clockRepository.findClockByActivity(LocalDate.now(),true, userId));
        return currentClock.get();
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

}
