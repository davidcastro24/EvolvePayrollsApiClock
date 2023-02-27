package com.davcode.clock.services;

import com.davcode.clock.models.ClockAudit;
import com.davcode.clock.models.User;
import com.davcode.clock.repositories.ClockAuditRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClockAuditService {

    private final ClockAuditRepository clockAuditRepository;

    @Autowired
    public ClockAuditService(ClockAuditRepository clockAuditRepository) {
        this.clockAuditRepository = clockAuditRepository;
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
        return clockAuditRepository.findClockAuditsByUserId(userId);
    }

    public List<ClockAudit> getAllByAuthUser(String authUserName){
        return clockAuditRepository.findClockAuditByAuthUserName(authUserName);
    }

    public void authorizeRequest(ClockAudit clockAudit){
        clockAudit.setAuthorizationStatus(true);
        clockAuditRepository.save(clockAudit);
    }

    public void denyRequest(ClockAudit clockAudit){
        clockAudit.setAuthorizationStatus(false);
        clockAuditRepository.save(clockAudit);
    }

    public void deleteClockAudit(Long id){
        clockAuditRepository.deleteById(id);
    }


}
