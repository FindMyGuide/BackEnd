package com.find_my_guide.main_member.member.domain.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

import com.find_my_guide.main_tour_product.tour_product.domain.Languages;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.lang.Nullable;

import java.util.List;

@Getter
@AllArgsConstructor
public class UpdateMemberRequest {

//    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,15}$", message = "영문,숫자,특수기호를 포함한 8자리 이상, 15자리 이하이어야 합니다.")
//    @Nullable
//    private String password;

    @Pattern(regexp = "^[a-zA-Zㄱ-힣0-9]*$", message = "닉네임에 특수문자를 포함할 수 없습니다.")
    @Nullable
    private String nickname;

    @Pattern(regexp = "^01(?:0|1|[6-9])-?(\\d{4})-?(\\d{4})$", message = "올바르지 않은 휴대폰 번호 형식입니다.")
    @Nullable
    private String phoneNumber;

    @Nullable
    private String nationalCertificationOfGuideYn;

    @Nullable
    private Integer guideExperience;

    @Nullable
    private String profilePicture;

    @Nullable
    private List<Languages> languages;

    @Nullable
    private String guideIntro;

}
