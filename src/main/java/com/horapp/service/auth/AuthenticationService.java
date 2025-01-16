package com.horapp.service.auth;

import com.horapp.persistence.entity.User;
import com.horapp.presentation.dto.auth.AuthenticationRequest;
import com.horapp.presentation.dto.auth.AuthenticationResponse;
import com.horapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthenticationService {
    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;


    private Map<String, Object> generateExtraClaims(User user) {
        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("username",user.getUsername());
        extraClaims.put("role",user.getRole());

        return extraClaims;
    }

    public AuthenticationResponse login(AuthenticationRequest autRequest) {

        Authentication authentication = new UsernamePasswordAuthenticationToken(
                autRequest.username(), autRequest.password()
        );

        authenticationManager.authenticate(authentication);

        User user = userService.findByUsername(autRequest.username());
        String jwt = jwtService.generateToken(user, generateExtraClaims(user));

        return new AuthenticationResponse(jwt);
    }


}
