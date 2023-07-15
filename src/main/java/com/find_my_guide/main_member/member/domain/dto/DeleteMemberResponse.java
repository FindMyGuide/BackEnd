package com.find_my_guide.main_member.member.domain.dto;

import com.find_my_guide.main_member.member.domain.entity.Member;
import lombok.Getter;

@Getter
public class DeleteMemberResponse {

    private String name;

    private String email;

    public DeleteMemberResponse(Member member) {
        this.name = member.getName();
        this.email = member.getEmail();
    }
}
