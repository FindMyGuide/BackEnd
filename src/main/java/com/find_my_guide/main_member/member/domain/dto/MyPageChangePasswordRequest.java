package com.find_my_guide.main_member.member.domain.dto;

import lombok.Data;

import javax.validation.constraints.Pattern;

@Data
public class MyPageChangePasswordRequest {

    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,15}$", message = "비밀번호는 영문,숫자,특수기호를 포함한 8자리 이상, 15자리 이하이어야 합니다.")
    private String password;

    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,15}$", message = "비밀번호는 영문,숫자,특수기호를 포함한 8자리 이상, 15자리 이하이어야 합니다.")
    private String newPassword;

    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,15}$", message = "비밀번호는 영문,숫자,특수기호를 포함한 8자리 이상, 15자리 이하이어야 합니다.")
    private String newPasswordAgain;

}
