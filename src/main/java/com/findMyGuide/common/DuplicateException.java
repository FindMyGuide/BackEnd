package com.findMyGuide.common;

import lombok.Getter;

@Getter
public class DuplicateException extends RuntimeException {

    private ErrorCode errorCode;

    public DuplicateException() {
    }

    public DuplicateException(String message, ErrorCode errorCode) {
        super(message + " is duplicated");
        this.errorCode = errorCode;
    }
}
