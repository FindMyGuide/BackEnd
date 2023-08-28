package com.find_my_guide.main_member.member.service;

import com.find_my_guide.main_member.common.ErrorCode;
import com.find_my_guide.main_member.common.NotFoundException;
import com.find_my_guide.main_member.member.domain.dto.PJTNameUserDetails;
import com.find_my_guide.main_member.member.domain.entity.Member;
import com.find_my_guide.main_member.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PJTNameUserDetailsService implements UserDetailsService {

    private final MemberService memberService;
    @Override
    public PJTNameUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            return new PJTNameUserDetails(memberService.findByEmail(username));
        } catch (Exception e) {
            throw new BadCredentialsException("id or password Wrong");
        }
    }
}
