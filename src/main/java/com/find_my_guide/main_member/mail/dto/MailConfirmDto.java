package com.find_my_guide.main_member.mail.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MailConfirmDto {
    @Email
    String email;
    String code;

}
