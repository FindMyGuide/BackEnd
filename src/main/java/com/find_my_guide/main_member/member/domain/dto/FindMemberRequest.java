package com.find_my_guide.main_member.member.domain.dto;

import lombok.Data;

import javax.validation.constraints.Pattern;

@Data
public class FindMemberRequest {

    @Pattern(regexp = "^[가-힣]{2,4}|[a-zA-Z]{2,10}\\s[a-zA-Z]{2,10}$", message = "올바르지 않은 이름 형식입니다.")
    private String name;

    @Pattern(regexp = "^01(?:0|1|[6-9])-?(\\d{4})-?(\\d{4})$", message = "올바르지 않은 휴대폰 번호 형식입니다.")
    private String phoneNumber;

}
