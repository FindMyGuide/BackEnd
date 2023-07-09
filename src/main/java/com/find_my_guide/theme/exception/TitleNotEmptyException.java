package com.find_my_guide.theme.exception;

public class TitleNotEmptyException extends RuntimeException{

    public TitleNotEmptyException() {
        super("반드시 입력 해야 합니다.");
    }
}
