package com.find_my_guide.main_member.member.domain.dto;


import com.find_my_guide.main_member.member.domain.entity.Gender;
import com.find_my_guide.main_member.member.domain.entity.Member;
import com.find_my_guide.main_tour_product.tour_product.domain.Languages;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreateMemberRequest {

    @Pattern(regexp = "^[가-힣]{2,4}|[a-zA-Z]{2,10}\\s[a-zA-Z]{2,10}$", message = "올바르지 않은 이름 형식입니다.")
    private String name;
    @Email(message = "이메일 형식이 올바르지 않습니다.")
    private String email;
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,15}$", message = "비밀번호는 영문,숫자,특수기호를 포함한 8자리 이상, 15자리 이하이어야 합니다.")
    private String password;
    @Pattern(regexp = "^[a-zA-Zㄱ-힣0-9]*$", message = "닉네임에 특수문자를 포함할 수 없습니다.")
    private String nickname;
    private String nationality;
    private String gender;
    private String birthDate;
    @Pattern(regexp = "^01(?:0|1|[6-9])-?(\\d{4})-?(\\d{4})$", message = "올바르지 않은 휴대폰 번호 형식입니다.")
    private String phoneNumber;
    private String nationalCertificationOfGuideYn;

    private String guideProfilePicture;
    private String guideIntroduction;
    private Integer guideExperience;
    private List<Languages> languages;


    public Member toMember(PasswordEncoder passwordEncoder) {
        return Member.builder()
                .name(name)
                .email(email)
                .password(passwordEncoder.encode(password))
                .nickname(nickname)
                .nationality(nationality)
                .gender(Gender.valueOf(gender))
                .birthDate(birthDate)
                .phoneNumber(phoneNumber)
                .nationalCertificationOfGuideYn(nationalCertificationOfGuideYn.equalsIgnoreCase("Y"))
                .profilePicture(guideProfilePicture)
                .guideIntro(guideIntroduction)
                .guideExperience(guideExperience)
                .languages(languages)
                .build();
    }
}
