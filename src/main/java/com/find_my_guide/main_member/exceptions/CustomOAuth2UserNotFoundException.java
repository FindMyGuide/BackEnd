package com.find_my_guide.main_member.exceptions;

public class CustomOAuth2UserNotFoundException extends RuntimeException {
    public CustomOAuth2UserNotFoundException() {
        super("OAuth2 user not found in the system.");
    }
}