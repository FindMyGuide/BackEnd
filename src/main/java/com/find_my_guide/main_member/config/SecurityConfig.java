package com.find_my_guide.main_member.config;

import com.find_my_guide.main_member.auth.LoginSuccessHandler;
import com.find_my_guide.main_member.member.service.MemberDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

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
            .formLogin()
                .usernameParameter("id")
                .passwordParameter("pwd")
                .loginPage("/login")    // 사용자 정의 로그인 페이지, default: /login
                .loginProcessingUrl("/login")   // 로그인 Form Action Url, default: /login
                .successHandler(loginSuccessHandler())
                .and()
            .logout()
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true)        //세션초기화
                .logoutSuccessUrl("/home")
                .and()
            //.oauth2Login()
            .build();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationSuccessHandler loginSuccessHandler() {
        return new LoginSuccessHandler();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(MemberDetailsService memberDetailsService) {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(memberDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());

        return authenticationProvider;
    }

}
