package com.example.ffservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.ffservice.service.TeamsService;

import java.util.Map;

@RestController
@RequestMapping("/teams")
public class TeamsController {

    @Autowired
    private TeamsService teamsService;

    Logger logger = LoggerFactory.getLogger(TeamsService.class);

    @PostMapping("/createTeam")
    @ResponseBody
    public Map<String, Object> createTeam(@RequestHeader(value = "username", required = false) String username,
            @RequestParam(name = "team_name") String team_name,
            @RequestParam(name = "team_desc") String team_desc,
            @RequestParam(name = "team_org") Long team_org) {
        try {
            return teamsService.createTeam(team_name, team_desc, team_org, username);
        } catch (Exception e) {
            logger.debug("createTeam route -- teamsController");
            Map<String, Object> res = Map.of("res", "FAIL");
            return res;
        }
    }

    @PostMapping("/sample")
    @ResponseBody
    public Map<String, Object> sample(@RequestHeader(value = "username", required = false) String username) {
        Map<String, Object> res = Map.of("res", "PASS", "username", username);
        return res;
    }
}
