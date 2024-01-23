package com.toy.knit.controller;

import com.toy.knit.request.auth.Login;
import com.toy.knit.request.auth.Signup;
import com.toy.knit.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/api/auth/signup")
    public void signup(@RequestBody Signup signup) {
        authService.signup(signup);
    }

    @PostMapping("/api/auth/login")
    public Long login(@RequestBody Login login) {
        Long memberId = authService.signIn(login);

        return memberId;
    }
}
