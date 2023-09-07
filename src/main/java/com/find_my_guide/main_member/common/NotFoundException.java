package com.find_my_guide.main_member.common;

import lombok.Getter;

@Getter
public class NotFoundException extends RuntimeException{

    private ErrorCode errorCode;

    public NotFoundException() {
    }

    public NotFoundException(String message) {
        super(message + " is not found");
    }
}
