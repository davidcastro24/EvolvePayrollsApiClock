package com.davcode.clock.controllers;

import com.davcode.clock.mappers.dto.ClockResponse;
import com.davcode.clock.mappers.dto.DtoMapper;
import com.davcode.clock.models.Clock;
import com.davcode.clock.services.ClockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/clock")
public class ClockController {

    private final ClockService clockService;

    @Autowired
    public ClockController(ClockService clockService) {
        this.clockService = clockService;
    }

    @PostMapping(path = "/{userId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void addClock(@PathVariable Long userId){
            clockService.addClock(userId);
    }

    @GetMapping(path = "/{clockId}")
    public ClockResponse getClock(@PathVariable Long clockId){
        return DtoMapper.clockToDto(clockService.getClock(clockId));
    }

    @GetMapping(path = "/user/{userId}")
    public List<ClockResponse> getClocksByUser(@PathVariable Long userId){
        return clockService.getAllByUserId(userId);
    }

    @PostMapping(path = "/automclock/{userId}")
    public ClockResponse postAutomaticSchedule(@PathVariable Long userId){
        return clockService.automaticTimeSet(userId);
    }

}
