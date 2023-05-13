package com.davcode.clock.repositories;

import com.davcode.clock.models.Break;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BreakRepository extends JpaRepository<Break,Long> {
    List<Break> findBreakByClockId(Long clockId);
    Break findBreakByActive(boolean active);
}
