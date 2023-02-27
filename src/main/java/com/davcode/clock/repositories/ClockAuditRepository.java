package com.davcode.clock.repositories;

import com.davcode.clock.models.ClockAudit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClockAuditRepository extends JpaRepository<ClockAudit,Long> {
    List<ClockAudit> findClockAuditByAuthUserName(String authUserName);

    @Query("SELECT c FROM ClockAudit c " +
            "JOIN c.clock cl JOIN cl.user u" +
            "WHERE u.user_id = :userId")
    List<ClockAudit> findClockAuditsByUserId(@Param("userId") Long userId);
}
