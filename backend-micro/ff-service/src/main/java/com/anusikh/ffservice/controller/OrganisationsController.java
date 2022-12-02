package com.anusikh.ffservice.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.anusikh.ffservice.service.OrganisationsService;

@RestController
@RequestMapping("/organisations")
public class OrganisationsController {

    @Autowired
    private OrganisationsService organisationsService;

    Logger logger = LoggerFactory.getLogger(OrganisationsService.class);

    @PostMapping("/createOrg")
    @ResponseBody
    public Map<String, Object> createOrg(@RequestHeader(value = "username", required = false) String username,
            @RequestParam(name = "org_name") String org_name) {
        try {
            return organisationsService.createOrg(org_name, username);
        } catch (Exception e) {
            logger.debug("createOrg route -- organisationsController");
            Map<String, Object> res = Map.of("res", "FAIL");
            return res;
        }
    }

}
