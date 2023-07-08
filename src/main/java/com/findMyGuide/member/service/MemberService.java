package com.findMyGuide.member.service;

import com.findMyGuide.common.DuplicateException;
import com.findMyGuide.common.ErrorCode;
import com.findMyGuide.common.NotFoundException;
import com.findMyGuide.member.domain.dto.CreateMemberRequest;
import com.findMyGuide.member.domain.dto.CreateMemberResponse;
import com.findMyGuide.member.domain.dto.ReadMemberRequest;
import com.findMyGuide.member.domain.dto.ReadMemberResponse;
import com.findMyGuide.member.domain.dto.UpdateMemberRequest;
import com.findMyGuide.member.domain.dto.UpdateMemberResponse;
import com.findMyGuide.member.domain.entity.Member;
import com.findMyGuide.member.repository.MemberRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class MemberService {

    private final MemberRepository memberRepository;

    private final PasswordEncoder passwordEncoder;

    @Transactional
    public CreateMemberResponse createMember(CreateMemberRequest memberRequest) {
        if(memberRepository.findByEmail(memberRequest.getEmail()).isPresent()) {
            log.error(memberRequest.getEmail() + " is duplicated");
            throw new DuplicateException(memberRequest.getEmail(), ErrorCode.DUPLICATION);
        }

        if(memberRepository.findByNickname(memberRequest.getNickname()).isPresent()) {
            log.error(memberRequest.getNickname() + " is duplicated");
            throw new DuplicateException(memberRequest.getNickname(), ErrorCode.DUPLICATION);
        }

        if(memberRepository.findByPhoneNumber(memberRequest.getPhoneNumber()).isPresent()) {
            log.error(memberRequest.getPhoneNumber() + " is duplicated");
            throw new DuplicateException(memberRequest.getPhoneNumber(), ErrorCode.DUPLICATION);
        }

        Member member = memberRepository.save(memberRequest.toMember(passwordEncoder));

        return new CreateMemberResponse(member);
    }

    public ReadMemberResponse readMember(ReadMemberRequest memberRequest) {
        Optional<Member> member = memberRepository.findByEmail(memberRequest.getEmail());

        if(member.isEmpty()) {
            log.error(memberRequest.getEmail() + " is not found");
            throw new NotFoundException(memberRequest.getEmail(), ErrorCode.NOT_FOUND);
        }

        return new ReadMemberResponse(member.get());
    }

    @Transactional
    public UpdateMemberResponse updateMember(UpdateMemberRequest memberRequest) {
        Optional<Member> member = memberRepository.findByEmail(memberRequest.getEmail());

        if(member.isEmpty()) {
            log.error(memberRequest.getEmail() + " is not found");
            throw new NotFoundException(memberRequest.getEmail(), ErrorCode.NOT_FOUND);
        }

        member.get()
            .update(passwordEncoder,
                memberRequest.getPassword(),
                memberRequest.getNickname(),
                memberRequest.getPhoneNumber(),
                memberRequest.getNationalCertificationOfGuideYn());

        return new UpdateMemberResponse(member.get());
    }
}
