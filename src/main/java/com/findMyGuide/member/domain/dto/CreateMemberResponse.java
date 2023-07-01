package com.findMyGuide.member.domain.dto;

import com.findMyGuide.member.domain.entity.Member;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class CreateMemberResponse {

    private Long idx;

    private String email;

    private String nickName;

    private String nationality;

    private String gender;

    private Integer age;

    private String phoneNumber;

    private String nationalCertificationOfGuideYn;

    public CreateMemberResponse(Member member) {
        this.idx = member.getIdx();
        this.email = member.getEmail();
        this.nickName = member.getNickName();
        this.nationality = member.getNationality();
        this.gender = member.getGender().getKrName();
        this.age = member.getAge();
        this.phoneNumber = member.getPhoneNumber();
        this.nationalCertificationOfGuideYn = member.getNationalCertificationOfGuideYn().toString();
    }
}
