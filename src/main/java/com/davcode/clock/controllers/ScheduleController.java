package com.davcode.clock.controllers;

import com.davcode.clock.models.Schedule;
import com.davcode.clock.services.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/schedule")
public class ScheduleController {

    private final ScheduleService scheduleService;

    @Autowired
    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @PostMapping
    public void addSchedule(@RequestBody Schedule schedule){
        scheduleService.addSchedule(schedule);
    }

    @GetMapping(path = "/{id}")
    public Schedule getSchedule(@PathVariable Long id){
        return scheduleService.getSchedule(id);
    }

    @GetMapping(path = "/getAll/{companyId}")
    public List<Schedule> getAllFromCompany(Long companyId){
        return scheduleService.getAllFromCompany(companyId);
    }

}
