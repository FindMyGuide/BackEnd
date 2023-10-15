package com.find_my_guide.main_member.member.domain.dto;

import com.find_my_guide.main_member.member.domain.entity.Member;
import com.find_my_guide.main_tour_product.common.validation_field.Title;
import com.find_my_guide.main_tour_product.tour_product.domain.Languages;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class GuideResponse {

    private Long guideId;
    private String guideName;
    private String guideEmail;
    private String gender;
    private boolean hasGuideCertification;
    private List<Title> tourProductTitles;
    private int guideExperience;
    private List<Languages> languages;
    private String guideIntro;

    private String guideCertification;
    private String profilePicture;


    public GuideResponse(Member member) {
        this.guideId = member.getIdx();
        this.guideName = member.getName();
        this.guideEmail = member.getEmail();
        this.gender = member.getGender().getKrName();
        this.hasGuideCertification = member.getNationalCertificationOfGuideYn();
        this.tourProductTitles = member.getTourHistoriesAsGuide().stream()
                .map(it -> it.getTourProduct().getTitle())
                .collect(Collectors.toList());
        this.guideExperience = (member.getGuideExperience() != null) ? member.getGuideExperience() : 0;
        this.languages = (member.getLanguages() != null) ? member.getLanguages() : new ArrayList<>();
        this.guideIntro = (member.getGuideIntro() != null) ? member.getGuideIntro() : "";
        this.profilePicture = (member.getProfilePicture() != null) ? member.getProfilePicture() : "";
        this.guideCertification = (member.getGuideCertification() != null) ? member.getGuideCertification() : "";
    }
}
