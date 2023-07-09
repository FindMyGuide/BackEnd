package com.find_my_guide.main_member.service;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.find_my_guide.main_member.member.domain.dto.CreateMemberRequest;
import com.find_my_guide.main_member.member.domain.dto.CreateMemberResponse;
import com.find_my_guide.main_member.member.domain.entity.Member;
import com.find_my_guide.main_member.member.repository.MemberRepository;
import com.find_my_guide.main_member.member.service.MemberService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@ExtendWith(MockitoExtension.class)
class MemberServiceTest {

    @InjectMocks
    MemberService memberService;

    @Mock
    MemberRepository memberRepository;

    Member member;
    CreateMemberRequest memberRequest;

    @BeforeEach
    void setUp() {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        memberRepository = mock(MemberRepository.class);
        memberService = new MemberService(memberRepository, passwordEncoder);

        memberRequest = new CreateMemberRequest(
            "abc@naver.com",
            "@qwer12345",
            "test",
            "KR",
            "MALE",
            "19980000",
            "010-1234-5678",
            "N"
        );

        member = memberRequest.toMember(passwordEncoder);
    }

    @Test
    @DisplayName("회원 등록 성공")
    void createMember() {
        //when
        when(memberRepository.save(any(Member.class))).thenReturn(member);
        CreateMemberResponse result = memberService.createMember(memberRequest);

        //then
        assertAll(
            () -> assertThat(result.getEmail()).isEqualTo(member.getEmail()),
            () -> assertThat(result.getNickname()).isEqualTo(member.getNickname()),
            () -> assertThat(result.getNationality()).isEqualTo(member.getNationality()),
            () -> assertThat(result.getGender()).isEqualTo(member.getGender().getKrName()),
            () -> assertThat(result.getBirthDate()).isEqualTo(member.getBirthDate()),
            () -> assertThat(result.getPhoneNumber()).isEqualTo(member.getPhoneNumber()),
            () -> assertThat(result.getNationalCertificationOfGuideYn()).isEqualTo(member.getNationalCertificationOfGuideYn().toString())
        );
    }
}