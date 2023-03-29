package com.davcode.clock.services;

import com.davcode.clock.exceptions.Exceptions;
import com.davcode.clock.models.Schedule;
import com.davcode.clock.repositories.ScheduleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;

    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    public void addSchedule(Schedule schedule){
        Optional<Schedule> conflicted = getConflictedSchedule(schedule);
        if (conflicted.isPresent())
            throw new Exceptions.ConflictedScheduleException("Schedule conflicts with existing schedule " +
                    "Start Time: " + conflicted.get().getStartTime() +
                    ",End Time: " + conflicted.get().getEndTime() +
                    ",Rate: " + conflicted.get().getRate());

        scheduleRepository.save(schedule);
    }

    public Optional<Schedule> getConflictedSchedule(Schedule schedule){
        return scheduleRepository
                .findAll()
                .stream()
                .filter(s -> isConflictedSchedule(s,schedule))
                .findFirst();
    }

    public boolean isConflictedSchedule(Schedule fromList, Schedule schedule){
        //s -> s.getStartTime().isBefore(schedule.getStartTime()) && s.getEndTime(schedule.getStartTime())
        if (fromList.getStartTime().isBefore(schedule.getStartTime()) && fromList.getEndTime().isAfter(schedule.getStartTime()))
            return true;
        if (fromList.getEndTime().isAfter(schedule.getEndTime()) && fromList.getStartTime().isBefore(schedule.getEndTime()))
            return true;
        if (fromList.getStartTime().equals(schedule.getStartTime()) || fromList.getEndTime().equals(schedule.getEndTime()))
            return true;
        return false;
    }

    public Schedule getSchedule(Long id){
        return scheduleRepository.findById(id).get();
    }

    public void deleteSchedule(Long id){
        scheduleRepository.deleteById(id);
    }

    public List<Schedule> getAllFromCompany(Long companyId){
        return scheduleRepository.findAllByCompany_Id(companyId);
    }

}
