package com.ericxie.travel_risk_api.controller;

import com.ericxie.travel_risk_api.model.auth.AuthResponse;
import com.ericxie.travel_risk_api.model.auth.LoginRequest;
import com.ericxie.travel_risk_api.model.auth.RegisterRequest;
import com.ericxie.travel_risk_api.model.auth.User;
import com.ericxie.travel_risk_api.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.status(201).body(userService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.status(200).body(userService.login(request));
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout() {
        return ResponseEntity.ok("Logged out successfully");
    }


}

