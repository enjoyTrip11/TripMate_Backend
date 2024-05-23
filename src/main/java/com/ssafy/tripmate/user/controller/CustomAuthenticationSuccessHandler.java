package com.ssafy.tripmate.user.controller;

import com.ssafy.tripmate.user.jwt.JwtTokenProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final JwtTokenProvider jwtTokenProvider;

    public CustomAuthenticationSuccessHandler(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        String jwt = jwtTokenProvider.generateToken(oAuth2User.getAttribute("id"));
        System.out.println("plzzzzzzzzzzzzzzzzzzzzz");
        System.out.println(jwt);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write("{\"token\": \"" + jwt + "\"}");
    }
}
