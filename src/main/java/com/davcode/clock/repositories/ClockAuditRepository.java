package com.davcode.clock.repositories;

import com.davcode.clock.models.ClockAudit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClockAuditRepository extends JpaRepository<ClockAudit,Long> {
    List<ClockAudit> findClockAuditByAuthUserName(String authUserName);

    /*@Query("SELECT c FROM ClockAudit c " +
            "JOIN c.clock cl JOIN cl.user u " +
            "WHERE u.userId = :userId")*/
    List<ClockAudit> findAllByClock_User_Id(Long userId);
    List<ClockAudit> findAllByCompanyId(Long companyId);
}
