package com.davcode.clock.services;

import com.davcode.clock.exceptions.Exceptions;
import com.davcode.clock.mappers.dto.ClockResponse;
import com.davcode.clock.mappers.dto.DtoMapper;
import com.davcode.clock.models.Clock;
import com.davcode.clock.models.ClockAudit;
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
    private final UserService userService;

    @Autowired
    public ClockService(ClockRepository clockRepository, UserService userService) {
        this.clockRepository = clockRepository;
        this.userService = userService;
    }

    public void addClock(Long userId){
        Clock clock = new Clock();
        User user = userService.getUserByIdInternal(userId);
        clock.setStartTime(LocalTime.now());
        clock.setActiveFlag(true);
        clock.setActiveDate(LocalDate.now());
        clock.setUser(user);
        clock.setUnderReview(false);
        if (user.isAutoScheduleAllowed())
            deactivateAllActiveClocksFromUser(user);
            clock.setEndTime(user.getEmployee().getAssignedEndTime());
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

    public void deactivateAllActiveClocksFromUser(User user){
        clockRepository.findClockByUserId(user.getId())
                .stream()
                .filter(Clock::isActiveFlag)
                .forEach(clock -> {
                    clock.setActiveFlag(false);
                    clockRepository.save(clock);
                });
    }

    public ClockResponse getCurrentClock(Long userId){
        Optional<Clock> currentClock = Optional.of(
                clockRepository.findClockByUserIdAndActiveFlagAndActiveDate(userId,
                        true,
                        LocalDate.now()
                )
        );
        if (currentClock.isPresent())
            return DtoMapper.clockToDto(currentClock.get());
        throw new Exceptions.ClockNotFoundException("No active clocks");
    }

    public List<Clock> getAllActiveClocksFromCompany(Long companyId){
        List<User> users = userService.getUsersFromCompany(companyId);
        return clockRepository.findByUserIdAndActiveFlag(
                users.stream().map(User::getId).collect(Collectors.toList()),
                true
        );
    }

    public void deleteClock(Long clockId){
        clockRepository.deleteById(clockId);
    }


   /* public void submitUpdateTimePeriod(LocalTime startTime, LocalTime endTime, Long clockId){
        if (startTime.isAfter(endTime) || startTime.equals(endTime))
            throw new Exceptions.StartTimeIsAfterEndTimeException("Check inputted times");
        ClockAudit clockAudit = new ClockAudit();
        clockAudit.setStartTime(startTime);
        clockAudit.setEndTime(endTime);
        clockAudit.setSubmitDate(LocalDate.now());
        clockAudit.setClock(getClock(clockId));
        clockAuditService.addClockAudit(clockAudit);
    }*/

    public void checkOut(Clock clock){
        clock.setActiveFlag(false);
        clock.setEndTime(LocalTime.now());
        clockRepository.save(clock);
    }

    public void updateTime(Long clockId, LocalTime startTime, LocalTime endTime){
        Clock clock = getClock(clockId);
        clock.setStartTime(startTime);
        clock.setEndTime(endTime);
        clock.setUnderReview(false);
        clockRepository.save(clock);
    }

    public void setUnderReview(Long clockId, boolean underReview){
        Clock clock = getClock(clockId);
        clock.setUnderReview(underReview);
        clockRepository.save(clock);
    }


}
