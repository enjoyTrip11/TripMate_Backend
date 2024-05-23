//package com.ssafy.tripmate.user.controller;
//
//import com.ssafy.tripmate.user.jwt.JwtTokenProvider;
//import com.ssafy.tripmate.user.service.CustomOAuth2UserService;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.oauth2.core.user.OAuth2User;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequiredArgsConstructor
//public class OAuth2Controller {
//
//    private final JwtTokenProvider jwtTokenProvider;
//
////    @GetMapping("/login/oauth2/code/google")
////    public ResponseEntity<String> googleLogin(@AuthenticationPrincipal OAuth2User oAuth2User) {
////        String jwt = jwtTokenProvider.generateToken(oAuth2User.getAttribute("id"));
////        System.out.println("---------------jwt2-------------------");
////        System.out.println(jwt);
////        System.out.println("----------------------------------");
////        return ResponseEntity.ok(jwt);
////    }
//
//    @PostMapping("/login/oauth2/code/google")
//    public ResponseEntity<String> doSocialLogin(@AuthenticationPrincipal UserDetails userDetails, HttpServletResponse response) {
//        String jwtToken = jwtTokenProvider.generateToken(userDetails.getUsername());
//        // 토큰을 URL 파라미터에 추가하여 Vue.js로 리디렉션
//        String redirectUrl = "http://localhost:8080/?token=" + jwtToken;
//        return ResponseEntity.status(HttpStatus.OK).header("Location", redirectUrl).build();
//    }
//}
