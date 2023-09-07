package com.find_my_guide.main_member.common;

import com.find_my_guide.main_member.member.domain.entity.Member;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
public class KakaoOAuthAttribute {
    private String id;
    private String email;
    private String nickname;
    // 필요한 다른 속성들...

    @Builder
    public KakaoOAuthAttribute(String id, String email, String nickname) {
        this.id = id;
        this.email = email;
        this.nickname = nickname;
    }

    public static KakaoOAuthAttribute ofKakao(String userNameAttributeName, Map<String, Object> attributes) {
        // 카카오 API로부터 반환된 사용자 정보는 attributes에 저장됩니다.
        Map<String, Object> kakaoAccount = (Map<String, Object>) attributes.get("kakao_account");
        Map<String, Object> profile = (Map<String, Object>) kakaoAccount.get("profile");

        return KakaoOAuthAttribute.builder()
                .id((String) attributes.get(userNameAttributeName))
                .email((String) kakaoAccount.get("email"))
                .nickname((String) profile.get("nickname"))
                .build();
    }

    public Member toEntity() {
        return Member.builder()
                .kakaoId(id)
                .email(email)
                .name(nickname)
                // 다른 필요한 속성들...
                .build();
    }
}
