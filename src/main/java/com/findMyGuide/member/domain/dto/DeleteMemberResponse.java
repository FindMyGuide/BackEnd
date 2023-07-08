package com.findMyGuide.member.domain.dto;

import com.findMyGuide.member.domain.entity.Member;
import lombok.Getter;

@Getter
public class DeleteMemberResponse {

    private String email;

    public DeleteMemberResponse(Member member) {
        this.email = member.getEmail();
    }
}
