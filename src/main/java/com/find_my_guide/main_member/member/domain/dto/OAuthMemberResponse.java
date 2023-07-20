package com.find_my_guide.main_member.member.domain.dto;

import com.find_my_guide.main_member.member.domain.entity.Member;
import lombok.Getter;

@Getter
public class OAuthMemberResponse {

    private String nickName;

    private String email;

    public OAuthMemberResponse(Member member) {
        this.nickName = member.getNickname();
        this.email = member.getEmail();
    }
}
