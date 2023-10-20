package com.find_my_guide.main_member.member.domain.dto;

import com.find_my_guide.main_member.member.domain.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginMemberResponse {

    private Long memberId;

    private String email;

    private String nickName;

    public LoginMemberResponse(Member member) {
        this.memberId = member.getIdx();
        this.email = member.getEmail();
        this.nickName = member.getNickname();
    }
}
