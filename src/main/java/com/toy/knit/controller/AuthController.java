package com.toy.knit.controller;

import com.toy.knit.request.auth.Login;
import com.toy.knit.request.auth.Signup;
import com.toy.knit.service.AuthService;
import com.toy.knit.service.jwt.JwtService;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final JwtService jwtService;

    @PostMapping("/api/auth/signup")
    public void signup(@RequestBody Signup signup) {
        authService.signup(signup);
    }

    @PostMapping("/api/auth/login")
    public ResponseEntity login(@RequestBody Login login, HttpServletResponse response) {
        Long id = authService.signIn(login);

        String token = jwtService.getToken("id", id);

        Cookie cookie = new Cookie("token", token);
        cookie.setHttpOnly(true);
        cookie.setPath("/");

        response.addCookie(cookie);

        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @GetMapping("/api/auth/check")
    public ResponseEntity check(@CookieValue(value = "token", required = false) String token) {
        Claims claims = jwtService.getClaims(token);

        if (claims != null) {
            int id = Integer.parseInt(claims.get("id").toString());
            return new ResponseEntity<>(id, HttpStatus.OK);
        }

        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @PostMapping("/api/auth/logout")
    public ResponseEntity logout( HttpServletResponse response) {
        Cookie cookie = new Cookie("token", null);
        cookie.setPath("/");
        cookie.setMaxAge(0);

        response.addCookie(cookie);
        return new ResponseEntity(HttpStatus.OK);
    }
}
