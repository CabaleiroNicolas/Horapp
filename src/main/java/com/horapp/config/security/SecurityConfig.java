package com.horapp.config.security;

import com.horapp.config.security.filter.JwtAuthenticationFilter;
import com.horapp.config.security.handler.CustomAccessDeniedHandler;
import com.horapp.config.security.handler.CustomAuthenticationEntryPoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final AuthenticationProvider authProvider;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final CustomAuthenticationEntryPoint authenticationEntryPoint;
    private final CustomAccessDeniedHandler customAccessDeniedHandler;

    public SecurityConfig(AuthenticationProvider authProvider, JwtAuthenticationFilter jwtAuthenticationFilter, CustomAuthenticationEntryPoint authenticationEntryPoint, CustomAccessDeniedHandler customAccessDeniedHandler) {
        this.authProvider = authProvider;
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
        this.authenticationEntryPoint = authenticationEntryPoint;
        this.customAccessDeniedHandler = customAccessDeniedHandler;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling(handling -> {
                    handling.authenticationEntryPoint(authenticationEntryPoint);
                    handling.accessDeniedHandler(customAccessDeniedHandler);
                })
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers(HttpMethod.POST, "/authentication/**").permitAll();
                    auth.requestMatchers(HttpMethod.POST, "/users").permitAll();
                    auth.requestMatchers("/daysAndTimes/**").permitAll();
                    auth.requestMatchers(HttpMethod.GET,"/majors").permitAll();

                    //Carreras de usuarios
                    auth.requestMatchers(HttpMethod.GET,"/majors/user").authenticated();
                    auth.anyRequest().authenticated();
                })
                .build();
    }
}
