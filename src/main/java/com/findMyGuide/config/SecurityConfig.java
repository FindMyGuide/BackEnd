package com.findMyGuide.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
            .httpBasic()
                .disable()      //disable 설정을 하지 않으면 Http basic Auth 기반의 로그인 창이 뜬다. 기본창을 뜨지 않게 할 때 사용, Jwt 인증방식을 이용할 때도 disable로 설정
            .csrf()
                .disable()           //csrf 토큰을 통해 web을 통해 들어오는 C, U, D를 막을 수 있음 지금은 rest api라서 disable이지만 브라우저를 통해 들어올때는 enable로 설정
            .authorizeHttpRequests()
                //.antMatchers("인증된 유저만 접속될 URL").authenticated()
                .anyRequest().permitAll()
                .and()
            //.oauth2Login()
            .build();
    }

}
