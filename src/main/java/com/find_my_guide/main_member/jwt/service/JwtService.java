package com.find_my_guide.main_member.jwt.service;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.find_my_guide.main_member.common.ErrorCode;
import com.find_my_guide.main_member.common.NotFoundException;
import com.find_my_guide.main_member.member.repository.MemberRepository;
import java.util.Date;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Getter
@RequiredArgsConstructor
@Slf4j
public class JwtService {

    @Value("${jwt.secretKey}")
    private String secretKey;

    @Value("${jwt.access.expiration}")
    private Long accessTokenExpirationPeriod;

    @Value("${jwt.refresh.expiration}")
    private Long refreshTokenExpirationPeriod;

    @Value("${jwt.access.header}")
    private String accessTokenHeader;

    @Value("${jwt.refresh.header}")
    private String refreshTokenHeader;

    private static final String ACCESS_TOKEN_SUBJECT = "AccessToken";   //액세스토큰 명
    private static final String REFRESH_TOKEN_SUBJECT = "RefreshToken";   //리프레시토큰 명
    private static final String EMAIL_CLAIM = "email";
    private static final String BEARER = "Bearer ";

    private final MemberRepository memberRepository;

    public String createAccessToken(String email) {
        Date now = new Date();
        return JWT.create()
            .withSubject(ACCESS_TOKEN_SUBJECT)
            .withExpiresAt(new Date(now.getTime() + accessTokenExpirationPeriod))
            .withClaim(EMAIL_CLAIM, email)          //private Claim, 이메일 외 다른 클레임 추가가 필요한 경우 추가가능
            .sign(Algorithm.HMAC512(secretKey));
    }

    public String createRefreshToken() {
        Date now = new Date();
        return JWT.create()
            .withSubject(REFRESH_TOKEN_SUBJECT)
            .withExpiresAt(new Date(now.getTime() + refreshTokenExpirationPeriod))
            .sign(Algorithm.HMAC512(secretKey));
    }

    /**
     * AccessToken 헤더에 담아 전송하는 메서드
     * @param response
     * @param accessToken
     */
    public void sendAccessToken(HttpServletResponse response, String accessToken) {
        response.setStatus(HttpServletResponse.SC_OK);

        setAccessTokenHeader(response, accessToken);
        log.info("재발급 AccessToken : {}", accessToken);
    }

    /**
     * AccessToken + RefreshToken 헤더에 담아 전송하는 메서드
     * @param response
     * @param accessToken
     * @param refreshToken
     */
    public void sendAccessAndRefreshToken(HttpServletResponse response, String accessToken, String refreshToken) {
        response.setStatus(HttpServletResponse.SC_OK);

        setAccessTokenHeader(response, accessToken);
        setRefreshTokenHeader(response, refreshToken);
        log.info("신규 발급 AccessToken : {}", accessToken);
        log.info("신규 발급 RefreshToken : {}", refreshToken);
    }

    /**
     * 헤더에서 AccessToken 추출하는 메서드
     * @param request
     * AccessToken 만료로 인해 헤더에 없을 수 있으므로 Optional로 return
     * Bearer XXX에서 Bearer를 제외하고 순수 토큰만 추출하기 위해 replace
     */
    public Optional<String> extractAccessToken(HttpServletRequest request) {
        return Optional.ofNullable(request.getHeader(accessTokenHeader))
            .filter(accessToken -> accessToken.startsWith(BEARER))
            .map(accessToken -> accessToken.replace(BEARER, ""));
    }

    /**
     * 헤더에서 RefreshToken 추출하는 메서드
     * @param request
     * RefreshToken 만료로 인해 헤더에 없을 수 있으므로 Optional로 return
     * Bearer XXX에서 Bearer를 제외하고 순수 토큰만 추출하기 위해 replace
     */
    public Optional<String> extractRefreshToken(HttpServletRequest request) {
        return Optional.ofNullable(request.getHeader(refreshTokenHeader))
            .filter(refreshToken -> refreshToken.startsWith(BEARER))
            .map(refreshToken -> refreshToken.replace(BEARER, ""));
    }

    /**
     * AccessToken 에서 Email 추출하는 메서드
     * @param accessToken
     * 추출하기 전에 검증필요
     * JWT.require()를 통해 검증기 생성 후 verify()로 검증
     */
    public Optional<String> extractEmail(String accessToken) {
        try {
            return Optional.ofNullable(JWT.require(Algorithm.HMAC512(secretKey))
                .build()
                .verify(accessToken)    //액세스 토큰 검증시 유효하지 않으면 예외 발생
                .getClaim(EMAIL_CLAIM)
                .asString());
        }catch (Exception e) {
            log.error("액세스 토큰이 유효하지 않습니다. {}", e.getMessage());
            return Optional.empty();
        }
    }

    /**
     * RefreshToken 저장하는 메서드
     * @param email
     * @param refreshToken
     * 회원가입시에는 RefreshToken이 발급되지 않는데 첫 로그인 할 경우에 DB에 저장
     */
    public void updateRefreshToken(String email, String refreshToken) {
        memberRepository.findByEmail(email)
            .ifPresentOrElse(
                member -> member.updateRefreshToken(refreshToken),          //해당 이메일 회원이 있는 경우
                () -> new NotFoundException(email, ErrorCode.NOT_FOUND)     //해당 이메일 회원이 없는 경우
            );
    }

    /**
     * 인증할 때마다 토큰을 검증하는 메서드
     * @param token
     */
    public boolean isTokenValid(String token) {
        try {
            JWT.require(Algorithm.HMAC512(secretKey))
                .build()
                .verify(token);
            return true;
        }catch (Exception e) {
            log.error("유효하지 않은 토큰입니다. {}", e.getMessage());
            return false;
        }
    }

    private void setAccessTokenHeader(HttpServletResponse response, String accessToken) {
        response.setHeader(accessTokenHeader, accessToken);
    }

    private void setRefreshTokenHeader(HttpServletResponse response, String refreshToken) {
        response.setHeader(refreshTokenHeader, refreshToken);
    }
}
