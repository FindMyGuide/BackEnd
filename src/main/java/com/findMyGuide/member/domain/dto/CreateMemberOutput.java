package com.findMyGuide.member.domain.dto;

import com.findMyGuide.member.domain.entity.Member;
import lombok.Getter;

@Getter
public class CreateMemberOutput {

    private Long idx;

    private String email;

    private String nickName;

    private String nationality;

    private String gender;

    private Integer age;

    private String phoneNumber;

    private String certificationYn;

    public CreateMemberOutput(Member member) {
        this.idx = member.getIdx();
        this.email = member.getEmail();
        this.nickName = member.getNickName();
        this.nationality = member.getNationality();
        this.gender = member.getGender().getKrName();
        this.age = member.getAge();
        this.phoneNumber = member.getPhoneNumber();
        this.certificationYn = member.getCertificationYn().toString();
    }
}
