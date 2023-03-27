package com.davcode.clock.repositories;

import com.davcode.clock.models.Company;
import com.davcode.clock.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyRepository extends JpaRepository<Company,Long> {

}
