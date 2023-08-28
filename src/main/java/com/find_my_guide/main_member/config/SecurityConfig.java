package com.find_my_guide.main_member.config;

import com.find_my_guide.main_member.filter.JwtAuthenticationFilter;
import com.find_my_guide.main_member.filter.JwtAuthenticationProvider;
import com.find_my_guide.main_member.jwt.service.JwtTokenUtil;
import com.find_my_guide.main_member.member.domain.entity.Member;
import com.find_my_guide.main_member.member.service.MemberService;
import com.find_my_guide.main_member.member.service.PJTNameUserDetailsService;
import com.find_my_guide.main_member.member.service.OAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.LogoutFilter;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationProvider jwtAuthenticationProvider;

    private final ApplicationContext context;


    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // custom authenticationProvider를 authetnicationManager를 통해 Bean 주입
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.authenticationProvider(jwtAuthenticationProvider);
        return authenticationManagerBuilder.build();
    }

    @Bean
    // join 과 login을 제외한 모든 요청에 인증(custom filter를 거치게) 사용.
    public SecurityFilterChain filterChain(
            @Autowired HttpSecurity http,
            @Autowired MemberService memberService,
            @Autowired JwtTokenUtil jwtTokenUtil) throws Exception {
        http
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/*")
                .permitAll()
//                .antMatchers("/api/find-my-guide/sign-in", "/api/find-my-guide/sign-up")
//                .permitAll()
//                .anyRequest()
//                .authenticated()
                .and()
                .exceptionHandling()
                .and()
                .addFilterBefore(context.getBean(JwtAuthenticationFilter.class), LogoutFilter.class)
                .cors();
        return http.build();
    }
}