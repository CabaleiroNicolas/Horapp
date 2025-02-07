package com.horapp.presentation.dto.auth;

public record AuthenticationRequest(
        String username,
        String password
) {
}
