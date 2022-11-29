package com.example.ffservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.ffservice.service.FfService;

import java.util.Map;

@RestController
@RequestMapping("/ff")
public class FfController {

    @Autowired
    private FfService ffService;

    Logger logger = LoggerFactory.getLogger(FfService.class);

    @PostMapping("/createTeam")
    @ResponseBody
    public Map<String, Object> createTeam(@RequestHeader(value = "username", required = false) String username,
            @RequestParam(name = "team_name") String team_name,
            @RequestParam(name = "team_desc") String team_desc,
            @RequestParam(name = "admins") String admins) {
        try {
            return ffService.createTeam(team_name, team_desc, admins);
        } catch (Exception e) {
            logger.debug("createTeam route -- FfController");
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
