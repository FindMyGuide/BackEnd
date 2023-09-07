package com.find_my_guide.main_member.member.controller;

import com.find_my_guide.main_member.member.service.OAuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MemberOauthController {

    private final OAuthService oAuthService;
    private final OAuth2AuthorizedClientService authorizedClientService;
    private final ClientRegistrationRepository clientRegistrationRepository;

    @GetMapping("/auth/kakao/callback")
    public String kakaoCallback(OAuth2AuthenticationToken authentication) {
        if (authentication == null) {
            log.error("Authentication is null!");
            return "error";
        }

        OAuth2AuthorizedClient authorizedClient = authorizedClientService.loadAuthorizedClient(
                authentication.getAuthorizedClientRegistrationId(), authentication.getName());

        if (authorizedClient == null) {
            log.error("AuthorizedClient is null!");
            return "error";
        }

        ClientRegistration registration = clientRegistrationRepository.findByRegistrationId(authentication.getAuthorizedClientRegistrationId());

        if (registration == null) {
            log.error("ClientRegistration is null!");
            return "error";
        }

        OAuth2User oAuth2User = oAuthService.loadUser(new OAuth2UserRequest(registration, authorizedClient.getAccessToken()));

        if (oAuth2User == null) {
            log.error("OAuth2User is null!");
            return "error";
        }

        // 로그인 후 리다이렉트하고 싶은 페이지로 리다이렉트합니다.
        return oAuth2User.getName();
    }

}
