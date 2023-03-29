package com.davcode.clock.repositories;

import com.davcode.clock.models.Clock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ClockRepository extends JpaRepository<Clock,Long> {
    List<Clock> findClockByUserId(Long userId);

    @Query("SELECT c FROM Clock c WHERE c.activeDate = :activeDate AND" +
            " c.activeFlag = :activeFlag AND" +
            " c.user.id = :userId")
    Clock findClockByActivity(@Param("activeDate")LocalDate activeDate,
                              @Param("activeFlag") boolean activeFlag,
                              @Param("userId") Long userId);

    Clock findClockByUserIdAndActiveFlagAndActiveDate(Long userId, boolean activeFlag, LocalDate activeDate);

    Clock findClockByActiveDate(LocalDate activeDate);

    List<Clock> findByUserIdInAndActiveFlag(List<Long> userIds, boolean activeFlag);

    /*@Query("SELECT * FROM Clock c JOIN User U ON c.companyid=u.companyid" )
    Clock findClockByActiveDateAndCompany(@Param("active_date") LocalDate activeDate,
                                          @Param("company_id") Long CompanyId);*/
}
