package com.ssafy.tripmate.user.controller;

import com.ssafy.tripmate.user.jwt.JwtTokenProvider;
import com.ssafy.tripmate.user.service.CustomOAuth2UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OAuth2Controller {

    private final JwtTokenProvider jwtTokenProvider;

    @GetMapping("/login/oauth2/code/google")
    public ResponseEntity<String> googleLogin(@AuthenticationPrincipal OAuth2User oAuth2User) {
        String jwt = jwtTokenProvider.generateToken(oAuth2User.getAttribute("id"));
        System.out.println("---------------jwt2-------------------");
        System.out.println(jwt);
        System.out.println("----------------------------------");
        return ResponseEntity.ok(jwt);
    }
}
