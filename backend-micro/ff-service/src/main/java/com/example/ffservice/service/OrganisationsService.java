package com.example.ffservice.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ffservice.entity.Organisations;
import com.example.ffservice.repository.OrganisationsRepository;

@Service
public class OrganisationsService {

    Logger logger = LoggerFactory.getLogger(OrganisationsService.class);

    @Autowired
    private OrganisationsRepository organisationsRepository;

    public Map<String, Object> createOrg(String org_name, String creator) {
        Map<String, Object> map = new HashMap<>();
        try {
            if (organisationsRepository.findIfOrg_NameExists(org_name)) {
                throw new Exception("organisation with the same name already exists");
            }
            organisationsRepository.save((new Organisations().builder()
                    .org_name(org_name).org_creator(creator)
                    .org_creation_date(new Date())).build());
            map.put("res", "PASS");
        } catch (Exception e) {
            logger.debug("createOrg -- organisationsService", e);
            map.put("res", "FAIL");
            map.put("message", e.getMessage());
        }
        return map;
    }

}
