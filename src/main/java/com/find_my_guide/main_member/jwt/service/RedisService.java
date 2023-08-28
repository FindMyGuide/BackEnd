package com.find_my_guide.main_member.jwt.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class RedisService {
    private final RedisTemplate<String, Object> redisTemplate;
    @Value("${jwt.expiration}")
    private int expiration;

    public String getToken(String key) {
        return (String) redisTemplate.opsForValue().get(key);
    }

    // key : value 가 토큰 : "logout" 인 쌍 redis에 저장.
    public void storeAccessToken(String token) {
        String msg = "logout";
        redisTemplate.opsForValue().set(token, msg);
        redisTemplate.expire(token, expiration, TimeUnit.SECONDS);
    }

    // key : value 가 userId : 토큰 인 쌍 redis에 저장.
    public void storeRefreshToken(String userId, String token) {
        redisTemplate.opsForValue().set(userId, token);
    }

    public void deleteToken(String userId) {
        redisTemplate.opsForValue().getAndDelete(userId);
    }

}
