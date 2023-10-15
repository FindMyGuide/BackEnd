package com.find_my_guide.main_tour_product.want_tour_product.domain;

public enum Vehicle {
    CAR("자동차"),
    WALK("도보"),
    BICYCLE("자전거"),
    MOTORCYCLE("오토바이"),
    PUBLIC_TRANSPORT("대중교통");


    private final String korean;

    Vehicle(String korean){
        this.korean = korean;
    }

    public String getKorean() {
        return korean;
    }

    public static Vehicle fromString(String input){
        for (Vehicle value : Vehicle.values()) {
            if (value.getKorean().equalsIgnoreCase(input) || value.name().equalsIgnoreCase(input)) {
                return value;
            }
        }
        throw new IllegalArgumentException("찾을 수 없음: " + input);
    }



}
