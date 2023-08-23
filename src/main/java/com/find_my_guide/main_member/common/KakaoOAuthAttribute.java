package com.find_my_guide.main_member.common;

import com.find_my_guide.main_member.member.domain.entity.Member;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class KakaoOAuthAttribute {

    private Map<String, Object> attributes;
    private String nameAttributeKey;
    private String name;
    private String email;

    public static KakaoOAuthAttribute ofKakao(String userNameAttributeName, Map<String, Object> attributes) {

        Map<String, Object> kakaoAccount = (Map<String, Object>) attributes.get("kakao_account");
        Map<String, Object> profile = (Map<String, Object>) kakaoAccount.get("profile");

        return KakaoOAuthAttribute.builder()
            .name((String) profile.get("nickname"))
            .email((String) kakaoAccount.get("email"))
            .nameAttributeKey(userNameAttributeName)
            .attributes(attributes)
            .build();
    }

    public Member toEntity() {
        return Member.builder()
            .nickname(name)
            .email(email)
            .nationalCertificationOfGuideYn(false)
            .build();
    }

}
