package com.argviewer.api.controllers;

import com.argviewer.domain.interfaces.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/Auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @GetMapping("/login")
    public ResponseEntity<String> login(@RequestBody String emailNickname, @RequestBody String senha) {
        String token = authService.login(emailNickname, senha);
        return ResponseEntity.ok(token);
    }

    @PostMapping("/validateToken")
    public ResponseEntity<Boolean> validateToken(@RequestBody String token) {
        boolean isTokenValid = authService.validateToken(token);
        return ResponseEntity.ok(isTokenValid);
    }
}
