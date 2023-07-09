package com.findMyGuide.member.domain.dto;

import com.findMyGuide.member.domain.entity.Member;
import lombok.Getter;

@Getter
public class ReadMemberResponse {

    private String email;

    private String nickname;

    private String nationality;

    private String gender;

    private String birthDate;

    private String phoneNumber;

    private String nationalCertificationOfGuideYn;

    public ReadMemberResponse(Member member) {
        this.email = member.getEmail();
        this.nickname = member.getNickname();
        this.nationality = member.getNationality();
        this.gender = member.getGender().getKrName();
        this.birthDate = member.getBirthDate();
        this.phoneNumber = member.getPhoneNumber();
        this.nationalCertificationOfGuideYn = member.getNationalCertificationOfGuideYn().toString();
    }
}
