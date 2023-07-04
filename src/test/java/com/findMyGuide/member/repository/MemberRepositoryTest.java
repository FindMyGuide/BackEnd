package com.findMyGuide.member.repository;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import com.findMyGuide.common.ErrorCode;
import com.findMyGuide.common.NotFoundException;
import com.findMyGuide.member.domain.dto.CreateMemberRequest;
import com.findMyGuide.member.domain.entity.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    private CreateMemberRequest memberRequest;

    @BeforeEach
    void setUp() {
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
    }

    @Test
    @DisplayName("회원이 DB에 저장되는지 테스트")
    void registerMember() {
        //given
        Member member = memberRequest.toMember();

        //when
        Member registerMember = memberRepository.save(memberRequest.toMember());

        //then
        assertAll(
            () -> assertThat(member.getEmail()).isEqualTo(registerMember.getEmail()),
            () -> assertThat(member.getNickname()).isEqualTo(registerMember.getNickname()),
            () -> assertThat(member.getNationality()).isEqualTo(registerMember.getNationality()),
            () -> assertThat(member.getGender()).isEqualTo(registerMember.getGender()),
            () -> assertThat(member.getAge()).isEqualTo(registerMember.getAge()),
            () -> assertThat(member.getPhoneNumber()).isEqualTo(registerMember.getPhoneNumber()),
            () -> assertThat(member.getNationalCertificationOfGuideYn()).isEqualTo(registerMember.getNationalCertificationOfGuideYn())
        );
    }

    @Test
    @DisplayName("이메일로 회원 조회")
    void findByEmail() {
        //given
        Member member = memberRepository.save(memberRequest.toMember());

        //when
        Member result = memberRepository.findByEmail(member.getEmail())
            .orElseThrow(
                () -> new NotFoundException(memberRequest.getEmail(), ErrorCode.NOT_FOUND));

        //then
        assertAll(
            () -> assertThat(result.getEmail()).isEqualTo(member.getEmail()),
            () -> assertThat(result.getNickname()).isEqualTo(member.getNickname()),
            () -> assertThat(result.getNationality()).isEqualTo(member.getNationality()),
            () -> assertThat(result.getGender()).isEqualTo(member.getGender()),
            () -> assertThat(result.getAge()).isEqualTo(member.getAge()),
            () -> assertThat(result.getPhoneNumber()).isEqualTo(member.getPhoneNumber()),
            () -> assertThat(result.getNationalCertificationOfGuideYn()).isEqualTo(member.getNationalCertificationOfGuideYn())
        );
    }

    @Test
    @DisplayName("닉네임으로 회원 조회")
    void findByNickname() {
        //given
        Member member = memberRepository.save(memberRequest.toMember());

        //when
        Member result = memberRepository.findByNickname(member.getNickname())
            .orElseThrow(
                () -> new NotFoundException(memberRequest.getNickname(), ErrorCode.NOT_FOUND));

        //then
        assertAll(
            () -> assertThat(result.getEmail()).isEqualTo(member.getEmail()),
            () -> assertThat(result.getNickname()).isEqualTo(member.getNickname()),
            () -> assertThat(result.getNationality()).isEqualTo(member.getNationality()),
            () -> assertThat(result.getGender()).isEqualTo(member.getGender()),
            () -> assertThat(result.getAge()).isEqualTo(member.getAge()),
            () -> assertThat(result.getPhoneNumber()).isEqualTo(member.getPhoneNumber()),
            () -> assertThat(result.getNationalCertificationOfGuideYn()).isEqualTo(member.getNationalCertificationOfGuideYn())
        );
    }

    @Test
    @DisplayName("휴대폰번호로 회원 조회")
    void findByPhoneNumber() {
        //given
        Member member = memberRepository.save(memberRequest.toMember());

        //when
        Member result = memberRepository.findByPhoneNumber(member.getPhoneNumber())
            .orElseThrow(
                () -> new NotFoundException(memberRequest.getPhoneNumber(), ErrorCode.NOT_FOUND));

        //then
        assertAll(
            () -> assertThat(result.getEmail()).isEqualTo(member.getEmail()),
            () -> assertThat(result.getNickname()).isEqualTo(member.getNickname()),
            () -> assertThat(result.getNationality()).isEqualTo(member.getNationality()),
            () -> assertThat(result.getGender()).isEqualTo(member.getGender()),
            () -> assertThat(result.getAge()).isEqualTo(member.getAge()),
            () -> assertThat(result.getPhoneNumber()).isEqualTo(member.getPhoneNumber()),
            () -> assertThat(result.getNationalCertificationOfGuideYn()).isEqualTo(member.getNationalCertificationOfGuideYn())
        );
    }
}