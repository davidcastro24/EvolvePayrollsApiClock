package com.davcode.clock.services;

import com.davcode.clock.models.Clock;
import com.davcode.clock.models.ClockAudit;
import com.davcode.clock.repositories.ClockAuditRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClockAuditService {

    private ClockAuditRepository clockAuditRepository;
    private ClockService clockService;

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

    public void authorizeRequest(Long id, String authUsername){
        ClockAudit clockAudit = getClockAuditById(id);
        clockAudit.setAccepted(true);
        clockAudit.setRejected(false);
        clockAudit.setAuthUserName(authUsername);
        clockAudit.setAuthorizationDate(LocalDate.now());
        clockAuditRepository.save(clockAudit);
        clockService.updateTime(
                clockAudit.getClock().getId(),
                clockAudit.getStartTime(),
                clockAudit.getEndTime()
        );
    }

    public void denyRequest(Long id, String authUsername){
        ClockAudit clockAudit = getClockAuditById(id);
        clockAudit.setAuthUserName(authUsername);
        clockAudit.setRejected(true);
        clockAudit.setAccepted(false);
        clockAuditRepository.save(clockAudit);
        clockService.setUnderReview(clockAudit.getClock().getId(),false);
    }

    public void deleteClockAudit(Long id){
        clockAuditRepository.deleteById(id);
    }

    public void submitRequest(Long clockId, LocalTime startTime, LocalTime endTime){
        Clock clock = clockService.getClock(clockId);
        ClockAudit clockAudit = new ClockAudit();
        clockAudit.setClock(clock);
        clockAudit.setSubmitDate(LocalDate.now());
        clockAudit.setStartTime(startTime);
        clockAudit.setEndTime(endTime);
        clockAudit.setRejected(false);
        clockAudit.setAccepted(false);
        addClockAudit(clockAudit);
        clockService.setUnderReview(clockId,true);
    }

    public List<ClockAudit> getAllByCompany(Long companyId){
        return clockAuditRepository.findAllByCompanyId(companyId).stream().filter(
                c -> !c.isAccepted() && !c.isRejected()
        ).collect(Collectors.toList());
    }


}
