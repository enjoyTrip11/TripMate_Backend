package com.ssafy.tripmate.user.controller;

import com.ssafy.tripmate.user.dto.User;
import com.ssafy.tripmate.user.dto.request.LoginRequest;
import com.ssafy.tripmate.user.dto.request.SignUpRequest;
import com.ssafy.tripmate.user.dto.response.AfterLoginResponse;
import com.ssafy.tripmate.user.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<AfterLoginResponse> signUp(@RequestBody SignUpRequest request) {
        return ResponseEntity.ok(userService.signUpMember(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AfterLoginResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(userService.login(request));
    }

    @GetMapping("/logout")
    public ResponseEntity<Void> logout(){
        userService.logout();
        return ResponseEntity.ok().build();
    }

    @GetMapping("/list")
    public ResponseEntity<List<User>> getAllFriends() {
        return ResponseEntity.ok(userService.getAllList());
    }
}
