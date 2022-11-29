package com.anusikh.authservice.repository;

import com.anusikh.authservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    @Query(value = "SELECT u FROM User u WHERE u.user_id = ?1")
    User findByUser_id(Long user_id);

    @Query(value = "SELECT case when count(u)> 0 then true else false end FROM User u WHERE u.username = ?1")
    Boolean findIfUsernameExists(String username);

    @Query(value = "SELECT case when count(u)> 0 then true else false end FROM User u WHERE u.email_id = ?1")
    Boolean findIfEmailExists(String email_id);
}
