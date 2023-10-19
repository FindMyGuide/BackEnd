package com.find_my_guide.main_member.member.domain.dto;

import com.find_my_guide.main_member.member.domain.entity.Member;
import com.find_my_guide.main_tour_product.tour_product.domain.Languages;
import com.find_my_guide.main_tour_product.tour_product.dto.TourProductResponse;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class GuideResponse {

    private Long guideId;
    private String guideName;
    private String guideEmail;
    private String gender;
    private boolean hasGuideCertification;
    private int guideExperience;
    private List<Languages> languages;
    private String guideIntro;
    private String guideCertification;
    private String profilePicture;
    private String tourProductTitle;

    private List<TourProductResponse> tourProductResponses;
        //가격, 언어, 타이틀
    public GuideResponse(Member member) {
        this.guideId = member.getIdx();
        this.guideName = member.getName() != null ? member.getName() : "";
        this.guideEmail = member.getEmail() != null ? member.getEmail() : "";
        this.gender = (member.getGender() != null && member.getGender().getKrName() != null) ? member.getGender().getKrName() : "";
        this.hasGuideCertification = member.getNationalCertificationOfGuideYn() != null ? member.getNationalCertificationOfGuideYn() : false; // 가정: getNationalCertificationOfGuideYn()의 반환 타입이 Boolean임
        this.guideExperience = member.getGuideExperience() != null ? member.getGuideExperience() : 0;
        this.languages = member.getLanguages() != null ? member.getLanguages() : new ArrayList<>();
        this.guideIntro = member.getGuideIntro() != null ? member.getGuideIntro() : "";
        this.profilePicture = member.getProfilePicture() != null ? member.getProfilePicture() : "";
        this.guideCertification = member.getGuideCertification() != null ? member.getGuideCertification() : ""; // 가정: getGuideCertification()의 반환 타입이 String임
    }


}
