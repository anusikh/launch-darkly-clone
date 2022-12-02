package com.anusikh.ffservice.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anusikh.ffservice.entity.Organisations;
import com.anusikh.ffservice.entity.Teams;
import com.anusikh.ffservice.repository.OrganisationsRepository;
import com.anusikh.ffservice.repository.TeamsRepository;

@Service
public class TeamsService {

    Logger logger = LoggerFactory.getLogger(TeamsService.class);

    @Autowired
    private TeamsRepository teamsRepository;

    @Autowired
    private OrganisationsRepository organisationsRepository;

    public Map<String, Object> createTeam(String team_name, String team_desc, Long team_org, String admins) {
        Map<String, Object> map = new HashMap<>();
        try {
            if (teamsRepository.findIfTeam_NameExists(team_name)) {
                throw new Exception("team with the same name already exists");
            }
            CompletableFuture<Organisations> completableFuture = CompletableFuture
                    .supplyAsync(() -> organisationsRepository.findById(team_org).orElse(null));
            while (!completableFuture.isDone()) {
            }
            if (completableFuture.get() == null) {
                throw new Exception("organisation has been deleted");
            }
            teamsRepository.save((Teams.builder()
                    .team_name(team_name).team_desc(team_desc).team_creator(admins)
                    .admins(Arrays.asList(admins)).team_org(completableFuture.get())).build());
            map.put("res", "PASS");
        } catch (Exception e) {
            logger.debug("createTeam -- teamsService", e);
            map.put("res", "FAIL");
            map.put("message", e.getMessage());
        }
        return map;
    }

}
