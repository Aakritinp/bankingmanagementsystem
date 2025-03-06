package com.bms.security_service.controller;

import com.bms.security_service.utils.JWTUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@Slf4j
public class TokenController {

    private final JWTUtil jwtUtil;

    @Autowired
    public TokenController(JWTUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @GetMapping("/token/validate")
    public Boolean validateJwtToken(@RequestParam String token) {
        return jwtUtil.validateToken(token);
    }

    @GetMapping("/token")
    public String createJwtToken(@RequestParam String id, @RequestParam String role) {
        return jwtUtil.generateToken(id, role);
    }
}
