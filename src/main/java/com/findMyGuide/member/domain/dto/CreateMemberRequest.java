package com.findMyGuide.member.domain.dto;

import com.findMyGuide.member.domain.entity.Gender;
import com.findMyGuide.member.domain.entity.Member;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateMemberRequest {

    @Email(message = "이메일 형식이 올바르지 않습니다.")
    private String email;
    @Pattern(regexp = "/^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,15}$/", message = "영문,숫자,특수기호를 포함한 8자리 이상이어야 합니다.")
    private String password;
    @Pattern(regexp = "/^[a-zA-Zㄱ-힣0-9]*$/", message = "특수문자를 포함할 수 없습니다.")
    private String nickName;
    private String nationality;
    private String gender;
    private Integer age;
    @Pattern(regexp = "^01(?:0|1|[6-9])-?(\\d{4})-?(\\d{4})$", message = "올바르지 않은 휴대폰 번호 형식입니다.")
    private String phoneNumber;
    private Boolean nationalCertificationOfGuideYn;

    public Member toMember() {
        return Member.builder()
            .email(email)
            .password(password) //Security 적용하면 PasswordEncoder로 감싸서 저장하기
            .nickName(nickName)
            .nationality(nationality)
            .gender(Gender.valueOf(gender))
            .age(age)
            .phoneNumber(phoneNumber)
            .nationalCertificationOfGuideYn(nationalCertificationOfGuideYn)
            .build();
    }
}
