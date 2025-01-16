package com.horapp.presentation.dto.auth;

import java.io.Serializable;

public record AuthenticationRequest(String username,
                                    String password)
        implements Serializable {
}
