package com.anusikh.authservice.service;

import com.anusikh.authservice.entity.User;
import com.anusikh.authservice.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserService implements UserDetailsService {

    Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    public Map<String, Object> registerUser(String username, String password, String email_id) {
        Map<String, Object> map = new HashMap<>();
        try {
            if (userRepository.findIfUsernameExists(username)) {
                throw new Exception("There is an account with this username");
            }
            if (userRepository.findIfEmailExists(email_id)) {
                throw new Exception("There is an account with this email");
            }
            User user = new User();
            user.setUsername(username);
            user.setPassword(password);
            user.setEmail_id(email_id);
            userRepository.save(user);
            map.put("res", "PASS");
        } catch (Exception e) {
            logger.debug("registerUser -- userService", e);
            map.put("res", "FAIL");
            map.put("message", e.getMessage());
        }

        return map;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (null == user) {
            throw new UsernameNotFoundException("username not found: " + username);
        }
        return user;
    }

    public Long getUser_Id(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (null == user) {
            throw new UsernameNotFoundException("username not found: " + username);
        }
        return user.getUser_id();
    }

    public User getUsernameFromUser_id(Long user_id) throws Exception {
        User user = userRepository.findByUser_id(user_id);
        if (null == user) {
            throw new Exception("user not found");
        }
        return user;
    }
}
