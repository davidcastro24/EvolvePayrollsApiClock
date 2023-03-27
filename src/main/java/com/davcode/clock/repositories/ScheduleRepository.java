package com.davcode.clock.repositories;

import com.davcode.clock.models.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule,Long> {
    List<Schedule> findAllByCompany_Id(Long companyId);
}
