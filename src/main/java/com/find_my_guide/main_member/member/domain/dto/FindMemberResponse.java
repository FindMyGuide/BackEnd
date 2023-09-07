package com.find_my_guide.main_member.member.domain.dto;


import com.find_my_guide.main_member.member.domain.entity.Member;
import lombok.Data;

@Data
public class FindMemberResponse {
    private String email;

    public FindMemberResponse(Member member){
        this.email = member.getEmail();
    }

}
