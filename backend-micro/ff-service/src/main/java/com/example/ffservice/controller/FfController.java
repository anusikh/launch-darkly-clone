package com.example.ffservice.controller;

import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/ff")
public class FfController {

    @PostMapping("/sample")
    @ResponseBody
    public Map<String, Object> sample(@RequestHeader(value = "username", required = false) String username) {
        Map<String, Object> res = Map.of("res", "PASS", "username", username);
        return res;
    }
}
