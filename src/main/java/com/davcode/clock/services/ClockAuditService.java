package com.davcode.clock.services;

import com.davcode.clock.models.Clock;
import com.davcode.clock.models.ClockAudit;
import com.davcode.clock.repositories.ClockAuditRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClockAuditService {

    private final ClockAuditRepository clockAuditRepository;
    private final ClockService clockService;

    @Autowired
    public ClockAuditService(ClockAuditRepository clockAuditRepository, ClockService clockService) {
        this.clockAuditRepository = clockAuditRepository;
        this.clockService = clockService;
    }

    public void addClockAudit(ClockAudit clockAudit){
        clockAuditRepository.save(clockAudit);
    }

    public ClockAudit getClockAuditById(Long id){
        return clockAuditRepository.findById(id).get();
    }

    public List<ClockAudit> getAll(){
        return clockAuditRepository.findAll();
    }

    public List<ClockAudit> getAllByUser(Long userId){
        return clockAuditRepository.findAllByClock_User_Id(userId);
    }

    public List<ClockAudit> getAllByAuthUser(String authUserName){
        return clockAuditRepository.findClockAuditByAuthUserName(authUserName);
    }

    public void authorizeRequest(Long id){
        ClockAudit clockAudit = getClockAuditById(id);
        clockAudit.setAccepted(true);
        clockAudit.setRejected(false);
        clockAuditRepository.save(clockAudit);

        Clock clock = clockService.getClock(clockAudit.getClock().getClockId());


    }

    public void denyRequest(ClockAudit clockAudit){
        clockAudit.setRejected(true);
        clockAudit.setAccepted(false);
        clockAuditRepository.save(clockAudit);
    }

    public void deleteClockAudit(Long id){
        clockAuditRepository.deleteById(id);
    }


}
