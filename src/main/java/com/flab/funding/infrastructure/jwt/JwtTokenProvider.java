package com.flab.funding.infrastructure.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtTokenProvider {

    private final SecretKey secretKey;

    public JwtTokenProvider(@Value("${jwt.secret}") String secretKey) {

        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        this.secretKey = Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateToken(Authentication authentication) {

        Map<String, Object> headers = new HashMap<>();
        headers.put("typ", "JWT");
        headers.put("alg", "HS512");

        Map<String, Object> claims = new HashMap<>();

        return Jwts.builder()
                .header().add(headers).and()
                .claims(claims)
                .signWith(this.secretKey, Jwts.SIG.HS512)
                .compact();
    }

    public Claims getClaims(String jwt) throws JwtException {

        return Jwts.parser()
                .verifyWith(this.secretKey)
                .build()
                .parseSignedClaims(jwt)
                .getPayload();
    }
}
