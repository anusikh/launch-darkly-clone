package com.anusikh.authservice.controllers;


import com.anusikh.authservice.service.UserService;
import com.anusikh.authservice.util.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Value("${server.port}")
    private String port;

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    Logger logger = LoggerFactory.getLogger(UserService.class);

    @PostMapping("/register")
    @ResponseBody
    public Map<String, Object> register(
            @RequestParam(name = "username") String username,
            @RequestParam(name = "password") String password,
            @RequestParam(name = "email_id") String email_id) {
        try {
            return userService.registerUser(username, passwordEncoder.encode(password), email_id);
        } catch (Exception e) {
            logger.debug("register route -- AuthController");
            Map<String, Object> res = Map.of("res", "FAIL");
            return res;
        }
    }

    @PostMapping("/getToken")
    @ResponseBody
    public Map<String, Object> getToken(
            @RequestParam(name = "username") String username,
            @RequestParam(name = "password") String password
    ) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    username,
                    password
            ));
        } catch (Exception e) {
            throw new AuthenticationException("Wrong username and password", e) {
            };
        }


        final UserDetails user = userService.loadUserByUsername(username);
        String jwt = jwtUtil.generateToken(user);
        Map<String, Object> res = new HashMap<>();
        res.put("jwt", jwt);

        return res;
    }

    @PostMapping("/getUser_Id")
    @ResponseBody
    public Map<String, Object> getUser_id(@RequestParam(name = "username") String username) throws Exception {
        Map<String, Object> res = new HashMap<>();
        try{
            Long user_id = userService.getUser_Id(username);
            res.put("user_id", user_id);
            return res;
        }catch(Exception e){
            throw new Exception("Something went wrong");
        }
    }

    @PostMapping("/sample")
    @ResponseBody
    public Map<String, Object> sample( @RequestHeader(value = "username", required = false) String username) {
        Map<String, Object> res = Map.of("res", "PASS", "username", username);
        return res;
    }


}
