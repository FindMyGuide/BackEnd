package com.findMyGuide.member.service;

import com.findMyGuide.common.DuplicateException;
import com.findMyGuide.common.ErrorCode;
import com.findMyGuide.member.domain.dto.CreateMemberRequest;
import com.findMyGuide.member.domain.dto.CreateMemberResponse;
import com.findMyGuide.member.domain.entity.Member;
import com.findMyGuide.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public CreateMemberResponse createMember(CreateMemberRequest memberRequest) {
        if(memberRepository.findByEmail(memberRequest.getEmail()).isPresent()) {
            throw new DuplicateException(memberRequest.getEmail(), ErrorCode.DUPLICATION);
        }

        if(memberRepository.findByNickName(memberRequest.getNickName()).isPresent()) {
            throw new DuplicateException(memberRequest.getNickName(), ErrorCode.DUPLICATION);
        }

        if(memberRepository.findByPhoneNumber(memberRequest.getPhoneNumber()).isPresent()) {
            throw new DuplicateException(memberRequest.getNickName(), ErrorCode.DUPLICATION);
        }

        Member member = memberRepository.save(memberRequest.toMember());

        return new CreateMemberResponse(member);
    }
}
