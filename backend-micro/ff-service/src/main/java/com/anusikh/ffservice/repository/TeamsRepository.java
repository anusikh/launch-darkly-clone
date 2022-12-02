package com.anusikh.ffservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.anusikh.ffservice.entity.Teams;

@Repository
public interface TeamsRepository extends JpaRepository<Teams, Long> {

    @Query(value = "SELECT case when count(t)> 0 then true else false end FROM Teams t WHERE t.team_name = ?1")
    Boolean findIfTeam_NameExists(String team_name);
}
