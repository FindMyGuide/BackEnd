package com.findMyGuide.member.domain.dto;

import javax.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ReadMemberRequest {

    @Email(message = "이메일 형식이 올바르지 않습니다.")
    private String email;

}
