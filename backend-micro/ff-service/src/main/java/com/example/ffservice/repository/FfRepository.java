package com.example.ffservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ffservice.entity.Teams;

@Repository
public interface FfRepository extends JpaRepository<Teams, Long> {

}
