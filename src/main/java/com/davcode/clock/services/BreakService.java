package com.davcode.clock.services;

import com.davcode.clock.exceptions.Exceptions;
import com.davcode.clock.models.Break;
import com.davcode.clock.repositories.BreakRepository;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;


@Service
public class BreakService {

    private BreakRepository breakRepository;

    public BreakService(BreakRepository breakRepository) {
        this.breakRepository = breakRepository;
    }

    public void addBreak(Break clockBreak){
        Optional.of(breakRepository.findBreakByActive(true)).ifPresent(b ->{
            throw new Exceptions.ActiveBreakAlreadyExists("There is already a break active since : " + b.getStartTime());
        });
        clockBreak.setStartTime(LocalTime.now());
        breakRepository.save(clockBreak);
    }

    public Break getBreak(Long id){
        return breakRepository.findById(id).get();
    }

    public void deleteBreak(Long id){
        breakRepository.deleteById(id);
    }

    public void stopBreak(Long id){
        Break clockBreak = breakRepository.findById(id).get();
        clockBreak.setEndTime(LocalTime.now());
        clockBreak.setActive(false);
        breakRepository.save(clockBreak);
    }

    public List<Break> getBreaksFromClock(Long clockId){
        return breakRepository.findBreakByClockId(clockId);
    }
    

}
