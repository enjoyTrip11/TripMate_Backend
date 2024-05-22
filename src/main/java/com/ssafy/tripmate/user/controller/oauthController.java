package com.ssafy.tripmate.user.controller;

import com.ssafy.tripmate.user.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.ClientRegistrations;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.view.RedirectView;

import java.util.HashMap;
import java.util.Map;

@RestController
public class oauthController {

    @Value("${spring.security.oauth2.client.registration.google.client-id}")
    private String clientId;

    @Value("${spring.security.oauth2.client.registration.google.client-secret}")
    private String clientSecret;

    @Value("${spring.security.oauth2.client.registration.google.redirect-uri}")
    private String redirectUri;

    private final JwtTokenProvider jwtTokenProvider;

    public oauthController(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @GetMapping("/oauth2/authorize")
    public RedirectView authorize() {
        String authorizationRequest = "https://accounts.google.com/o/oauth2/auth" +
                "?client_id=" + clientId +
                "&redirect_uri=" + redirectUri +
                "&response_type=code" +
                "&scope=openid%20email%20profile";
        return new RedirectView(authorizationRequest);
    }

    @GetMapping("/login/oauth2/code/google")
    public String getGoogleAccessToken(@RequestParam("code") String code) {
        RestTemplate restTemplate = new RestTemplate();

        Map<String, String> params = new HashMap<>();
        params.put("client_id", clientId);
        params.put("client_secret", clientSecret);
        params.put("redirect_uri", redirectUri);
        params.put("grant_type", "authorization_code");
        params.put("code", code);

        String accessTokenUrl = "https://oauth2.googleapis.com/token";

        Map<String, Object> response = restTemplate.postForObject(accessTokenUrl, params, Map.class);
        String accessToken = (String) response.get("access_token");

        // 사용자 정보 요청
        String userInfoUrl = "https://www.googleapis.com/oauth2/v1/userinfo?access_token=" + accessToken;
        Map<String, Object> userInfo = restTemplate.getForObject(userInfoUrl, Map.class);

        // JWT 토큰 생성 및 반환
        String jwtToken = jwtTokenProvider.generateToken(userInfo.get("email").toString());
        return jwtToken;  // 클라이언트에 JWT 토큰 반환
    }

    @Bean
    public ClientRegistrationRepository clientRegistrationRepository() {
        ClientRegistration registration = ClientRegistrations.fromIssuerLocation("https://accounts.google.com")
                .clientId(clientId)
                .clientSecret(clientSecret)
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .redirectUri(redirectUri)
                .scope("openid", "email", "profile")
                .clientName("Google")
                .build();
        return new InMemoryClientRegistrationRepository(registration);
    }
}
