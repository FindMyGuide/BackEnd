package com.find_my_guide.main_member.member.service;

import com.find_my_guide.main_member.common.DuplicateException;
import com.find_my_guide.main_member.common.ErrorCode;
import com.find_my_guide.main_member.common.NotFoundException;
import com.find_my_guide.main_member.jwt.service.JwtTokenUtil;
import com.find_my_guide.main_member.jwt.service.RedisService;
import com.find_my_guide.main_member.member.domain.dto.*;
import com.find_my_guide.main_member.member.domain.entity.Member;
import com.find_my_guide.main_member.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true) //TODO 지우기
@Slf4j
public class MemberService {

    private final MemberRepository memberRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtTokenUtil jwtTokenUtil;

    private final RedisService redisService;

    private final TokenService tokenService;

    @Transactional
    public CreateMemberResponse createMember(CreateMemberRequest memberRequest) {
        isDuplicated(findByEmailHere(memberRequest.getEmail()), memberRequest.getEmail());

        isDuplicated(memberRepository.findByNickname(memberRequest.getNickname()), memberRequest.getNickname());

        isDuplicated(memberRepository.findByPhoneNumber(memberRequest.getPhoneNumber()), memberRequest.getPhoneNumber());

        Member member = memberRepository.save(memberRequest.toMember(passwordEncoder));

        return new CreateMemberResponse(member);
    }

    public void logout(final String token, final String userId) {
        redisService.deleteToken(userId);
        redisService.storeAccessToken(token);
    }


    public ReadMemberResponse readMember(String email) {
        Optional<Member> member = findByEmailHere(email);

        isExistedEmail(member, email);

        return new ReadMemberResponse(member.get());
    }

    @Transactional
    public UpdateMemberResponse updateMember(String email, UpdateMemberRequest memberRequest) {
        Optional<Member> member = findByEmailHere(email);

        isExistedEmail(member, email);

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
        Optional<Member> member = findByEmailHere(email);

        isExistedEmail(member, email);

        memberRepository.delete(member.get());
        return new DeleteMemberResponse(member.get());
    }

    public HashMap<String, Object> login(final LoginMemberRequest loginMemberRequest) {
        String userId = loginMemberRequest.getEmail();
        String password = loginMemberRequest.getPassword();
        Member member = findByEmail(userId);
        if (passwordEncoder.matches(password, member.getPassword())) {
            HashMap<String, Object> stringObjectHashMap = tokenService.generateTokens(userId);
            return stringObjectHashMap;
        }
        return null;
    }


    private boolean isRightPassword(LoginMemberRequest loginRequest, Member member) {
        if (!passwordEncoder.matches(loginRequest.getPassword(), member.getPassword())) {
            log.error("Invalid password for email: " + loginRequest.getEmail());
            throw new IllegalArgumentException("Invalid password");
        }
        return true;
    }

    private void isExistedEmail(Optional<Member> memberOpt, String loginRequest) {
        if (memberOpt.isEmpty()) {
            log.error(loginRequest + "is not found");
            throw new NotFoundException(loginRequest, ErrorCode.NOT_FOUND);
        }
    }

    private void isDuplicated(Optional<Member> memberRequest, String memberRequest1) {
        if (memberRequest.isPresent()) {
            log.error(memberRequest1 + " is duplicated");
            throw new DuplicateException(memberRequest1, ErrorCode.DUPLICATION);
        }
    }

    public Member findByEmail(String email) {
        return memberRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException());
    }

    private Optional<Member> findByEmailHere(String email) {
        return memberRepository.findByEmail(email);
    }
}
