package com.findMyGuide.common;

import lombok.Getter;

@Getter
public class NotFoundException extends RuntimeException{

    private ErrorCode errorCode;

    public NotFoundException() {
    }

    public NotFoundException(String message, ErrorCode errorCode) {
        super(message + " is not found");
        this.errorCode = errorCode;
    }
}
