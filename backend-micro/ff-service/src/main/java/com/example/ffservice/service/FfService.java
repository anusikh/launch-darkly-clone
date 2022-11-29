package com.example.ffservice.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ffservice.entity.Teams;
import com.example.ffservice.repository.FfRepository;

@Service
public class FfService {

    Logger logger = LoggerFactory.getLogger(FfService.class);

    @Autowired
    private FfRepository ffRepository;

    public Map<String, Object> createTeam(String team_name, String team_desc, String admins) {
        Map<String, Object> map = new HashMap<>();
        try {
            Teams team = new Teams();
            team.setTeam_name(team_name);
            team.setTeam_desc(team_desc);
            team.setAdmins(Arrays.asList(admins));
            ffRepository.save(team);
            map.put("res", "PASS");
        } catch (Exception e) {
            logger.debug("createTeam -- ffService", e);
            map.put("res", "FAIL");
            map.put("message", e.getMessage());
        }
        return map;
    }

}
