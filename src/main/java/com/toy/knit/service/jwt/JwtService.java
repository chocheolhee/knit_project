package com.toy.knit.service.jwt;

import io.jsonwebtoken.Claims;

public interface JwtService {
    String getToken(String key, Object value);

    Claims getClaims(String token);
}
