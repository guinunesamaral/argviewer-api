package com.argviewer.domain.model.requests.auth;

public record RegisterRequest(String name, String email, String password) {
}
