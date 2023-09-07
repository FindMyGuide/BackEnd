package com.find_my_guide.main_member.member.domain.dto;

import lombok.Data;

@Data
public class ResetPasswordRequest {
    private String code;
    private String newPassword;
}
