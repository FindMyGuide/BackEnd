package com.find_my_guide.main_member.member.service;

import com.find_my_guide.main_member.common.DuplicateException;
import com.find_my_guide.main_member.common.ErrorCode;
import com.find_my_guide.main_member.common.KakaoOAuthAttribute;
import com.find_my_guide.main_member.member.domain.dto.OAuthMemberResponse;
import com.find_my_guide.main_member.member.domain.entity.Member;
import com.find_my_guide.main_member.member.repository.MemberRepository;
import java.util.Collections;
import java.util.Optional;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class OAuthService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {


    private final MemberRepository memberRepository;
    private final HttpSession httpSession;
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails()
            .getUserInfoEndpoint().getUserNameAttributeName();

        KakaoOAuthAttribute attribute = KakaoOAuthAttribute.ofKakao(userNameAttributeName,
            oAuth2User.getAttributes());

        OAuthMemberResponse member = createOAuthMember(attribute);
        httpSession.setAttribute("member", member);

        return new DefaultOAuth2User(
            Collections.singleton(new SimpleGrantedAuthority("member")),
            attribute.getAttributes(),
            attribute.getNameAttributeKey()
        );
    }

    private OAuthMemberResponse createOAuthMember(KakaoOAuthAttribute attribute) {
        Optional<Member> member = memberRepository.findByEmail(attribute.getEmail());
        if(member.isPresent()) {
            return new OAuthMemberResponse(member.get());
        }

        Member saveMember = memberRepository.save(attribute.toEntity());

        return new OAuthMemberResponse(saveMember);
    }
}
