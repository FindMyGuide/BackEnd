package com.find_my_guide.main_member.member.service;

import com.find_my_guide.main_member.common.ErrorCode;
import com.find_my_guide.main_member.common.NotFoundException;
import com.find_my_guide.main_member.member.domain.entity.Member;
import com.find_my_guide.main_member.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.findByEmail(username)
            .orElseThrow(() -> new NotFoundException(username, ErrorCode.NOT_FOUND));

        return User.builder()
            .username(member.getEmail())
            .password(member.getPassword())
            .build();
    }
}
