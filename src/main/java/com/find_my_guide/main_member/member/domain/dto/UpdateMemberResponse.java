package com.find_my_guide.main_member.member.domain.dto;

import com.find_my_guide.main_member.member.domain.entity.Member;
import lombok.Getter;

import java.util.Optional;

@Getter
public class UpdateMemberResponse {

    private String email;

    private String nickname;

    private String phoneNumber;

    private String nationalCertificationOfGuideYn;

    public UpdateMemberResponse(Member member) {
        this.email = Optional.ofNullable(member.getEmail()).orElse(null);
        this.nickname = Optional.ofNullable(member.getNickname()).orElse(null);
        this.phoneNumber = Optional.ofNullable(member.getPhoneNumber()).orElse(null);
        this.nationalCertificationOfGuideYn = Optional.ofNullable(member.getNationalCertificationOfGuideYn())
                .map(Object::toString)
                .orElse(null);

    }
}
