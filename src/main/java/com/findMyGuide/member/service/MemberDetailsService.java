package com.findMyGuide.member.service;

import com.findMyGuide.common.ErrorCode;
import com.findMyGuide.common.NotFoundException;
import com.findMyGuide.member.domain.entity.Member;
import com.findMyGuide.member.repository.MemberRepository;
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
