package com.anusikh.authservice.controllers;


import com.anusikh.authservice.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController   {

    @Value("${server.port}")
    private String port;

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    Logger logger = LoggerFactory.getLogger(UserService.class);

    @PostMapping("/register")
    @ResponseBody
    public Map<String, Object> getInfo(
            @RequestParam(name = "username") String username,
            @RequestParam(name = "password") String password,
            @RequestParam(name = "email_id") String email_id){
        try{
            return userService.registerUser(username, passwordEncoder.encode(password), email_id);
        }catch(Exception e){
            logger.debug("register route -- AuthController");
            Map<String, Object> res = Map.of("res","FAIL");
            return res;
        }
    }
}
