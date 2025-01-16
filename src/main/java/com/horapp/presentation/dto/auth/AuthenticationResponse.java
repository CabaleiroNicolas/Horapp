package com.horapp.presentation.dto.auth;

import java.io.Serializable;

public record AuthenticationResponse(String jwt) implements Serializable {

}
