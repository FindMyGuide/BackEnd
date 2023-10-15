package com.find_my_guide.main_member.member.domain.dto;

import com.find_my_guide.main_member.member.domain.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MemberInfoResponse {

    private Long id;
    private String nickname;
    private String email;

    public MemberInfoResponse(Member member) {
        this.id = member.getIdx();
        this.nickname = member.getNickname();
        this.email = member.getEmail();
    }
}
