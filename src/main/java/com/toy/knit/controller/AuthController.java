package com.toy.knit.controller;

import com.toy.knit.request.auth.Login;
import com.toy.knit.request.auth.Signup;
import com.toy.knit.service.AuthService;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.SecretKey;

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
//        SecretKey key = Jwts.SIG.HS256.key().build();
//
//        return Jwts.builder().subject("Joe").signWith(key).compact();
    }
}
