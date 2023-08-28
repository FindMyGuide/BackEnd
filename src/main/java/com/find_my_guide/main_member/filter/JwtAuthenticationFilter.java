package com.find_my_guide.main_member.filter;

import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.find_my_guide.main_member.jwt.service.JwtTokenUtil;
import com.find_my_guide.main_member.jwt.service.RedisService;
import com.find_my_guide.main_member.member.domain.dto.PJTNameUserDetails;
import com.find_my_guide.main_member.member.domain.entity.Member;
import com.find_my_guide.main_member.member.repository.MemberRepository;
import com.find_my_guide.main_member.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenUtil jwtTokenUtil;

    private final RedisService redisService;

    private final MemberService memberService;

    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String token = request.getHeader(JwtTokenUtil.HEADER_STRING);

        logger.info("Received Token: " + token);  // 로그 추가


        if (token != null) {
            try {

                // 이미 로그아웃 된 상태면 접속 못하게함.
                if ("logout".equals(redisService.getToken(token))) {
                    logger.info("logout되어 만료된 토큰입니다.");
                    throw new JWTDecodeException("logout되어 만료된 토큰입니다.");
                }
                Authentication authentication = getAuthentication(token);

                // securityContextHolder에 로그인한 인증 정보 넣음.
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
            // 잘못된 jwt인 경우.
            catch (JWTDecodeException ex) {
                logger.error("Invalid JWT: " + ex.getMessage());  // 로그 추가

                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("Invalid JWT");
                return;
            }
            // jwt의 유효기간이 만료된 경우
            catch (TokenExpiredException ex) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("Expired JWT");
                return;
            } catch (Exception ex) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }

        }
        chain.doFilter(request, response);
    }


    public Authentication getAuthentication(String token) throws Exception {
        logger.info("Starting token verification.");


        JWTVerifier verifier = jwtTokenUtil.getVerifier();
        // "Bearer "제거한 string (순수 Token)을 decode.
        logger.info("After obtaining verifier.");


        DecodedJWT decodedJWT = verifier.verify(token.replace(JwtTokenUtil.TOKEN_PREFIX, ""));

        logger.info("After verifying token.");

        String userId = decodedJWT.getSubject();
        if (userId != null) {
            Member member = memberService.findByEmail(userId);
            if (member != null) {
                PJTNameUserDetails userDetails = new PJTNameUserDetails(member);
                // 얻어온 user 정보를 바탕으로 authentication 생성.
                UsernamePasswordAuthenticationToken jwtAuthentication = new UsernamePasswordAuthenticationToken(userId, null, userDetails.getAuthorities());
                jwtAuthentication.setDetails(userDetails);
                return jwtAuthentication;
            } else {
                logger.warn("User not found in database.");
            }
        } else {
            logger.warn("UserID not extracted from token.");
        }
        logger.warn("Returning null from getAuthentication.");

        return null;
    }

}
