package com.argviewer.services;

import com.argviewer.domain.interfaces.services.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Override
    public String login(String emailNickname, String senha) {
        return null;
    }

    @Override
    public boolean validateToken(String token) {
        return false;
    }
}
