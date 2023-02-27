package com.davcode.clock.controllers;

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
    public ResponseEntity<String> addClock(@PathVariable Long userId){
        try {
            clockService.addClock(userId);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Incorrect Data");
        }
        return ResponseEntity.status(HttpStatus.OK).body("Success");
    }

    @GetMapping(path = "/{clockId}")
    public Clock getClock(@PathVariable Long clockId){
        return clockService.getClock(clockId);
    }

    @GetMapping(path = "/user/{userId}")
    public List<Clock> getClocksByUser(@PathVariable Long userId){
        return clockService.getAllByUserId(userId);
    }

}
