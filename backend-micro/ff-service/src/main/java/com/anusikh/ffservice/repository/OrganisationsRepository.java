package com.anusikh.ffservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.anusikh.ffservice.entity.Organisations;

@Repository
public interface OrganisationsRepository extends JpaRepository<Organisations, Long> {

    @Query(value = "SELECT case when count(o)> 0 then true else false end FROM Organisations o WHERE o.org_name = ?1")
    Boolean findIfOrg_NameExists(String org_name);
}
