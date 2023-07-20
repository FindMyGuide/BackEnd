package com.find_my_guide.main_member.common;

import com.find_my_guide.main_member.member.domain.entity.Member;
import java.util.Map;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class KakaoOAuthAttribute {

    private Map<String, Object> attributes;
    private String nameAttributeKey;
    private String name;
    private String email;

    public static KakaoOAuthAttribute ofKakao(String userNameAttributeName, Map<String, Object> attributes) {

        Map<String, Object> response = (Map<String, Object>) attributes.get("kakao_account");
        Map<String, Object> account = (Map<String, Object>) attributes.get("profile");

        return KakaoOAuthAttribute.builder()
            .name((String) account.get("nickname"))
            .email((String) response.get("email"))
            .attributes(response)
            .nameAttributeKey(userNameAttributeName)
            .build();
    }

    public Member toEntity() {
        return Member.builder()
            .nickname(name)
            .email(email)
            .build();
    }

}
