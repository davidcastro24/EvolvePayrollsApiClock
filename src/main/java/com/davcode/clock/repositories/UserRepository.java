package com.davcode.clock.repositories;

import com.davcode.clock.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findByUserName(String userName);
    List<User> findAllByEmployee_Company_Id(Long companyId);
}
