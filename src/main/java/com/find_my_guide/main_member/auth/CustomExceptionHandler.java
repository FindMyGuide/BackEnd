package com.find_my_guide.main_member.auth;

import com.find_my_guide.main_member.exceptions.CustomOAuth2UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.URI;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(CustomOAuth2UserNotFoundException.class)
    public ResponseEntity<Object> handleOAuth2UserNotFoundException(CustomOAuth2UserNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.FOUND).location(URI.create("/find-my-guide/sign-up")).build();
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
