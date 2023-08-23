package com.find_my_guide.main_member.member.domain.dto;

import com.find_my_guide.main_member.member.domain.entity.Member;
import lombok.Getter;

@Getter
public class UpdateMemberResponse {

    private String email;

    private String nickname;

    private String phoneNumber;

    private String nationalCertificationOfGuideYn;

    public UpdateMemberResponse(Member member) {
        this.email = member.getEmail();
        this.nickname = member.getNickname();
        this.phoneNumber = member.getPhoneNumber();
        this.nationalCertificationOfGuideYn = member.getNationalCertificationOfGuideYn().toString();
    }
}
