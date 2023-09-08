package com.find_my_guide.main_member.member.domain.dto;

import com.find_my_guide.main_member.member.domain.entity.Member;
import com.find_my_guide.main_tour_product.common.validation_field.Title;
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
    public GuideResponse(Member member) {
        this.guideId = member.getIdx();
        this.guideName = member.getName();
        this.guideEmail = member.getEmail();
        this.gender = member.getGender().getKrName();
        this.hasGuideCertification = member.getNationalCertificationOfGuideYn();
        this.tourProductTitles = member.getTourHistoriesAsGuide().stream()
                .map(it -> it.getTourProduct().getTitle())
                .collect(Collectors.toList());
    }
}
