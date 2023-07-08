package com.findMyGuide.member.domain.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.lang.Nullable;

@Getter
@AllArgsConstructor
public class UpdateMemberRequest {

    @Email(message = "이메일 형식이 올바르지 않습니다.")
    private String email;

    @Pattern(regexp = "/^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,15}$/", message = "영문,숫자,특수기호를 포함한 8자리 이상이어야 합니다.")
    @Nullable
    private String password;

    @Pattern(regexp = "/^[a-zA-Zㄱ-힣0-9]*$/", message = "특수문자를 포함할 수 없습니다.")
    @Nullable
    private String nickname;

    @Pattern(regexp = "^01(?:0|1|[6-9])-?(\\d{4})-?(\\d{4})$", message = "올바르지 않은 휴대폰 번호 형식입니다.")
    @Nullable
    private String phoneNumber;

    @Nullable
    private String nationalCertificationOfGuideYn;
}
