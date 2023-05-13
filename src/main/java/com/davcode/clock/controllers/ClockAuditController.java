package com.davcode.clock.controllers;

import com.davcode.clock.models.ClockAudit;
import com.davcode.clock.services.ClockAuditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "api/vi/clock-audit")
public class ClockAuditController {

    private ClockAuditService clockAuditService;

    @Autowired
    public ClockAuditController(ClockAuditService clockAuditService) {
        this.clockAuditService = clockAuditService;
    }

    @PostMapping
    public void addClockAudit(@RequestBody ClockAudit clockAudit){
        clockAuditService.addClockAudit(clockAudit);
    }

    @GetMapping(path = "/{id}")
    public ClockAudit getClockAudit(@PathVariable Long id){
        return clockAuditService.getClockAuditById(id);
    }

    @GetMapping(path = "/user/{userId}")
    public List<ClockAudit> getAllByUser(@PathVariable Long userId){
        return clockAuditService.getAllByUser(userId);
    }

    @PutMapping(path = "/authorize/{id}")
    public void authorizeRequest(@PathVariable Long id, @RequestBody String authUsername){
        clockAuditService.authorizeRequest(id,authUsername);
    }

    @PutMapping(path = "/deny/{id}")
    public void denyRequest(@PathVariable Long id, @RequestBody String authUsername){
        clockAuditService.denyRequest(id,authUsername);
    }

    @PostMapping(path = "/submit/{clockId}")
    public void submit(@PathVariable Long clockId, @RequestBody Map<String, LocalTime> times){
        clockAuditService.submitRequest(
                clockId,
                times.get("startTime"),
                times.get("endTime")
        );
    }
}
