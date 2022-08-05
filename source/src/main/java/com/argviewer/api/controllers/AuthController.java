package com.argviewer.api.controllers;

import com.argviewer.domain.interfaces.services.UsuarioService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.argviewer.domain.model.external.requests.auth.LoginRequest;

@RestController
@RequestMapping("/api/Auth")
public class AuthController {

    private UsuarioService usuarioBusiness;

    @GetMapping("/login")
    public String login(@RequestBody LoginRequest request) {
        return request.toString();
    }
}
