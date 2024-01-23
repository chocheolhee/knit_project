package com.toy.knit.service.jwt;

import io.jsonwebtoken.*;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtServiceImpl implements JwtService {

    private static final String SECRET_KEY = "123snv!07fg#12gz24e7zxhjkcvh98#$&TY*sghjfzJhfkgS123";

    @Override
    public String getToken(String key, Object value) {
        Date expireTime = new Date();
        expireTime.setTime(expireTime.getTime() + 1000 * 60 * 5);
        byte[] secretByteKey = DatatypeConverter.parseBase64Binary(SECRET_KEY);
        SignatureAlgorithm sa = SignatureAlgorithm.HS256;
        Key signKey = new SecretKeySpec(secretByteKey, sa.getJcaName());

        HashMap<String, Object> headerMap = new HashMap<>();
        headerMap.put("type", "JWT");
        headerMap.put("alg", "HS256");

        Map<String, Object> map = new HashMap<>();
        map.put(key, value);

        JwtBuilder builder = Jwts.builder().setHeader(headerMap)
                .setClaims(map)
                .setExpiration(expireTime)
                .signWith(signKey, SignatureAlgorithm.HS256);

        return builder.compact();
    }

    @Override
    public Claims getClaims(String token) {
        if (token != null && !token.isEmpty()) {
            try {
                byte[] secretByteKey = DatatypeConverter.parseBase64Binary(SECRET_KEY);
                SignatureAlgorithm sa = SignatureAlgorithm.HS256;
                Key signKey = new SecretKeySpec(secretByteKey, sa.getJcaName());
                return Jwts.parser().setSigningKey(signKey).build().parseClaimsJws(token).getBody();
            } catch (ExpiredJwtException e) {
                // 만료됨
            } catch (JwtException e) {
                // 유효하지 않음
            }
        }

        return null;
    }
}
