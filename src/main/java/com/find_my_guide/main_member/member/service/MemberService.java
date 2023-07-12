package com.find_my_guide.main_member.member.service;

import com.find_my_guide.main_member.common.DuplicateException;
import com.find_my_guide.main_member.common.ErrorCode;
import com.find_my_guide.main_member.common.NotFoundException;
import com.find_my_guide.main_member.member.domain.dto.*;
import com.find_my_guide.main_member.member.domain.entity.Member;
import com.find_my_guide.main_member.member.repository.MemberRepository;

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
        if (findByEmail(memberRequest.getEmail()).isPresent()) {
            log.error(memberRequest.getEmail() + " is duplicated");
            throw new DuplicateException(memberRequest.getEmail(), ErrorCode.DUPLICATION);
        }

        if (memberRepository.findByNickname(memberRequest.getNickname()).isPresent()) {
            log.error(memberRequest.getNickname() + " is duplicated");
            throw new DuplicateException(memberRequest.getNickname(), ErrorCode.DUPLICATION);
        }

        if (memberRepository.findByPhoneNumber(memberRequest.getPhoneNumber()).isPresent()) {
            log.error(memberRequest.getPhoneNumber() + " is duplicated");
            throw new DuplicateException(memberRequest.getPhoneNumber(), ErrorCode.DUPLICATION);
        }

        Member member = memberRepository.save(memberRequest.toMember(passwordEncoder));

        return new CreateMemberResponse(member);
    }

    public ReadMemberResponse readMember(String email) {
        Optional<Member> member = findByEmail(email);

        if (member.isEmpty()) {
            log.error(email + " is not found");
            throw new NotFoundException(email, ErrorCode.NOT_FOUND);
        }

        return new ReadMemberResponse(member.get());
    }

    @Transactional
    public UpdateMemberResponse updateMember(String email, UpdateMemberRequest memberRequest) {
        Optional<Member> member = findByEmail(email);

        if (member.isEmpty()) {
            log.error(email + " is not found");
            throw new NotFoundException(email, ErrorCode.NOT_FOUND);
        }

        member.get()
                .update(passwordEncoder,
                        memberRequest.getPassword(),
                        memberRequest.getNickname(),
                        memberRequest.getPhoneNumber(),
                        memberRequest.getNationalCertificationOfGuideYn());

        return new UpdateMemberResponse(member.get());
    }

    @Transactional
    public DeleteMemberResponse deleteMember(String email) {
        Optional<Member> member = findByEmail(email);

        if (member.isEmpty()) {
            log.error(email + " is not found");
            throw new NotFoundException(email, ErrorCode.NOT_FOUND);
        }

        memberRepository.delete(member.get());
        return new DeleteMemberResponse(member.get());
    }

    public LoginMemberResponse login(LoginMemberRequest loginRequest) {
        Optional<Member> memberOpt = findByEmail(loginRequest.getEmail());

        if (memberOpt.isEmpty()) {
            log.error(loginRequest.getEmail() + " not found");
            throw new NotFoundException(loginRequest.getEmail(), ErrorCode.NOT_FOUND);
        }

        Member member = memberOpt.get();
        if (!passwordEncoder.matches(loginRequest.getPassword(), member.getPassword())) {
            log.error("Invalid password for email: " + loginRequest.getEmail());
            throw new IllegalArgumentException("Invalid password");
        }

        return new LoginMemberResponse(member.getEmail(), member.getNickname());
    }

    private Optional<Member> findByEmail(String email) {
        return memberRepository.findByEmail(email);
    }
}
