package com.find_my_guide.main_member.member.domain.dto;

import com.find_my_guide.main_member.member.domain.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.net.PasswordAuthentication;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginMemberRequest {

    private String email;

    private String password;

    public Member toMember(PasswordEncoder passwordEncoder) {
        return Member.builder()
                .email(email)
                .password(passwordEncoder.encode(password))
                .build();
    }


}
