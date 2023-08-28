package com.find_my_guide.main_member.jwt.service;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.find_my_guide.main_member.member.repository.MemberRepository;

import java.time.Instant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Slf4j
public class JwtTokenUtil {


    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";

    public static final String ISSUER = "findmyguide.com";


    private final Algorithm cryptoAlgorithm;

    public JwtTokenUtil(@Value("${jwt.secretKey}") String secretkey) {
        this.cryptoAlgorithm = Algorithm.HMAC256(secretkey.getBytes());
    }

    public JWTVerifier getVerifier() {
        log.info("Getting verifier.");
        JWTVerifier verifier = JWT
                .require(cryptoAlgorithm)
                .withIssuer(JwtTokenUtil.ISSUER)
                .build();
        log.info("Verifier obtained.");
        return verifier;
    }

    // userId를 Subject로 expiration을 가진 토큰 생성
    public String generateToken(String userId, int expiration) {
        final Instant now = Instant.now();
        return JwtTokenUtil.TOKEN_PREFIX + JWT.create()
                .withSubject(userId)
                .withExpiresAt(now.plusSeconds(expiration))
                .withIssuer(JwtTokenUtil.ISSUER)
                .sign(cryptoAlgorithm);
    }

    public String getUserIdFromToken(String token) {
        DecodedJWT decodedJWT = JWT.decode(token.replace(TOKEN_PREFIX, ""));
        return decodedJWT.getSubject();
    }
}
