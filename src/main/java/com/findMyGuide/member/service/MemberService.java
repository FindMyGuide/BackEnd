package com.findMyGuide.member.service;

import com.findMyGuide.member.domain.dto.CreateMemberInput;
import com.findMyGuide.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public void createMember(CreateMemberInput memberInput) {
    }
}
