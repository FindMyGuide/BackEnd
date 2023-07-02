package com.findMyGuide.member.service;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.findMyGuide.member.domain.dto.CreateMemberRequest;
import com.findMyGuide.member.domain.dto.CreateMemberResponse;
import com.findMyGuide.member.domain.entity.Member;
import com.findMyGuide.member.repository.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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
        memberRepository = mock(MemberRepository.class);
        memberService = new MemberService(memberRepository);

        memberRequest = new CreateMemberRequest(
            "abc@naver.com",
            "@qwer12345",
            "test",
            "KR",
            "MALE",
            25,
            "010-1234-5678",
            false
        );

        member = memberRequest.toMember();
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
            () -> assertThat(result.getAge()).isEqualTo(member.getAge()),
            () -> assertThat(result.getPhoneNumber()).isEqualTo(member.getPhoneNumber()),
            () -> assertThat(result.getNationalCertificationOfGuideYn()).isEqualTo(member.getNationalCertificationOfGuideYn().toString())
        );
    }
}