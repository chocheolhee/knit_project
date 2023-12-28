package com.toy.knit.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;

@Configuration
public class PasswordEncoderConfig {

    private static final SCryptPasswordEncoder encoder = new SCryptPasswordEncoder(
            16, 8, 1, 32, 64
    );

    public String encodePassword(String rawPassword) {
        return encoder.encode(rawPassword);
    }

    public boolean matches(String rawPassword, String encodePassword) {
        return encoder.matches(rawPassword, encodePassword);
    }
}
