package com.find_my_guide.main_member.member.domain.dto;

import com.find_my_guide.main_member.member.domain.entity.Member;
import com.find_my_guide.main_tour_product.tour_product.domain.Languages;
import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@ToString
public class CreateMemberResponse {

    private Long idx;

    private String name;

    private String email;

    private String nickname;

    private String nationality;

    private String gender;

    private String birthDate;

    private String phoneNumber;


    private String nationalCertificationOfGuideYn;

    private String guideProfilePicture;
    private String guideIntroduction;
    private Integer guideExperience;
    private List<Languages> languages = new ArrayList<>();

    public CreateMemberResponse(Member member) {
        this.idx = member.getIdx();
        this.name = member.getName();
        this.email = member.getEmail();
        this.nickname = member.getNickname();
        this.nationality = member.getNationality();
        this.gender = member.getGender().getKrName();
        this.birthDate = member.getBirthDate();
        this.phoneNumber = member.getPhoneNumber();
        this.nationalCertificationOfGuideYn = member.getNationalCertificationOfGuideYn().toString();
        this.guideExperience = member.getGuideExperience();
        this.guideIntroduction = member.getGuideIntro();
        this.guideProfilePicture = member.getProfilePicture();
        this.languages = member.getLanguages();
    }
}
