package com.find_my_guide.main_tour_product.theme.exception;

public class TitleNotEmptyException extends RuntimeException{

    public TitleNotEmptyException() {
        super("반드시 입력 해야 합니다.");
    }
}
