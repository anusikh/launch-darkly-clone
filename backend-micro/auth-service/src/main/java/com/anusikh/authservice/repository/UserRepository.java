package com.anusikh.authservice.repository;

import com.anusikh.authservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    @Query(value = "SELECT case when count(u)> 0 then true else false end FROM User u WHERE u.username = ?1")
    Boolean findIfUsernameExists(String username);
}
