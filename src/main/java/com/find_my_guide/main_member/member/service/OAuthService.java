package com.find_my_guide.main_member.member.service;

import com.find_my_guide.main_member.common.DuplicateException;
import com.find_my_guide.main_member.common.ErrorCode;
import com.find_my_guide.main_member.common.KakaoOAuthAttribute;
import com.find_my_guide.main_member.exceptions.CustomOAuth2UserNotFoundException;
import com.find_my_guide.main_member.member.domain.dto.OAuthMemberResponse;
import com.find_my_guide.main_member.member.domain.entity.Member;
import com.find_my_guide.main_member.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class OAuthService extends DefaultOAuth2UserService {

    private final MemberRepository memberRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        Map<String, Object> attributes = new HashMap<>(oAuth2User.getAttributes());

        KakaoOAuthAttribute attribute = KakaoOAuthAttribute.ofKakao("id", attributes);

        Member member = createOAuthMember(attribute);

        return new DefaultOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")),
                attributes,
                "email"
        );
    }

    private Member createOAuthMember(KakaoOAuthAttribute attribute) {
        Optional<Member> memberOptional = memberRepository.findByKakaoId(attribute.getId());

        if (memberOptional.isPresent()) {
            return memberOptional.get();
        } else {
            // 기존 회원이 아닐 경우 예외를 발생시켜 회원가입 엔드포인트로 리다이렉트하도록 합니다.
            throw new CustomOAuth2UserNotFoundException();
        }
    }
}
