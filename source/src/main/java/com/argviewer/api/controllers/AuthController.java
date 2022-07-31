package com.argviewer.api.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.argviewer.domain.model.requests.auth.LoginRequest;
import com.argviewer.domain.model.requests.auth.RegisterRequest;

@RestController
@RequestMapping("/api/Auth")
public class AuthController {

    @GetMapping("/login")
    public String login(@RequestBody LoginRequest request) {
        return request.toString();
    }

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest request) {
        return request.toString();
    }
}
