package com.horapp.service.auth;

import com.horapp.persistence.entity.User;
import com.horapp.presentation.dto.auth.AuthenticationRequest;
import com.horapp.presentation.dto.auth.AuthenticationResponse;
import com.horapp.presentation.dto.request.UserRequestDTO;
import com.horapp.presentation.dto.response.UserResponseDTO;
import com.horapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
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

    public UserResponseDTO save(UserRequestDTO userRequestDTO){
        User user = userService.save(userRequestDTO);
        String jwt = jwtService.generateToken(user, generateExtraClaims(user));
        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setUsername(user.getUsername());
        userResponseDTO.setName(user.getName());
        userResponseDTO.setLastname(user.getLastname());
        userResponseDTO.setEmail(user.getEmail());
        userResponseDTO.setId(user.getIdUser());
        userResponseDTO.setJwt(jwt);
        return userResponseDTO;
    }

    private Map<String, Object> generateExtraClaims(User user) {
        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("username",user.getUsername());
        extraClaims.put("role",user.getRole());

        return extraClaims;
    }

    public AuthenticationResponse login(AuthenticationRequest autRequest) {

        Authentication authentication = new UsernamePasswordAuthenticationToken(
                autRequest.getUsername(), autRequest.getPassword()
        );

        authenticationManager.authenticate(authentication);

        UserDetails user = userService.findByUsername(autRequest.getUsername());
        String jwt = jwtService.generateToken(user, generateExtraClaims((User) user));

        AuthenticationResponse authRsp = new AuthenticationResponse();
        authRsp.setJwt(jwt);

        return authRsp;
    }

    public boolean validateToken(String jwt) {
        try{
            jwtService.extractUsername(jwt);
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

}
