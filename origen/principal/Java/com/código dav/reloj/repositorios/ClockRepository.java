package com.davcode.clock.repositories;

import com.davcode.clock.models.Clock;
import jdk.vm.ci.meta.Local;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ClockRepository extends JpaRepository<Clock,Long> {
    List<Clock> findClockByUserId(Long userId);

    @Query("SELECT e FROM Clock WHERE e.active_date = :active_date AND" +
            " e.active_flag = :active_flag AND" +
            " e.user_id = :user_id")
    Clock findClockByActivity(@Param("active_date")LocalDate activeDate,
                              @Param("active_flag") boolean activeFlag,
                              @Param("user_id") Long userId);

    Clock findClockByActiveDate(LocalDate activeDate);

    //Necesitamos obtener los clocks de cierta fecha y company id
    @Query("SELECT * FROM Clock c JOIN User U ON c.companyid=u.companyid" )
    Clock findClockByActiveDateAndCompany(@Param("active_date") LocalDate activeDate,
                                          @Param("company_id") Long CompanyId);
}
