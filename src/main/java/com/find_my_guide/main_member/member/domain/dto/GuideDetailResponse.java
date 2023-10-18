package com.find_my_guide.main_member.member.domain.dto;

import com.find_my_guide.main_member.member.domain.entity.Gender;
import com.find_my_guide.main_member.member.domain.entity.Member;
import com.find_my_guide.main_tour_product.tour_history_manager.domain.TourHistoryManager;
import com.find_my_guide.main_tour_product.tour_product.domain.Languages;
import com.find_my_guide.main_tour_product.tour_product.dto.TourProductResponse;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Data
public class GuideDetailResponse {

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

    private List<TourProductResponse> tourProductResponses;
    public GuideDetailResponse(Member member, List<TourProductResponse> tourProductResponses) {
        this.guideId = Optional.ofNullable(member.getIdx()).orElse(0L); // Assuming 0 is a suitable default for guideId
        this.guideName = Optional.ofNullable(member.getName()).orElse("");
        this.guideEmail = Optional.ofNullable(member.getEmail()).orElse("");
        this.gender = Optional.ofNullable(member.getGender())
                .map(Gender::getKrName)  // Assuming Gender::getKrName method exists
                .orElse("");
        this.hasGuideCertification = Optional.ofNullable(member.getNationalCertificationOfGuideYn()).orElse(false);
        this.guideExperience = Optional.ofNullable(member.getGuideExperience()).orElse(0);
        this.languages = Optional.ofNullable(member.getLanguages()).orElse(new ArrayList<>());
        this.guideIntro = Optional.ofNullable(member.getGuideIntro()).orElse("");
        this.profilePicture = Optional.ofNullable(member.getProfilePicture()).orElse("");
        this.guideCertification = Optional.ofNullable(member.getGuideCertification()).orElse("");
        this.tourProductResponses = Optional.ofNullable(tourProductResponses).orElse(new ArrayList<>());
    }

}
