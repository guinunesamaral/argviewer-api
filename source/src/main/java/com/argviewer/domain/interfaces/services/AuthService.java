package com.argviewer.domain.interfaces.services;

public interface AuthService {

    String login(String emailNickname, String senha);

    boolean validateToken(String token);
}
