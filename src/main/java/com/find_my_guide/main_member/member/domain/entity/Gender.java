package com.find_my_guide.main_member.member.domain.entity;

public enum Gender {

    MALE ("남", "Male"),
    FEMALE ("여", "Female");

    //front에서 값 출력시 값.krName으로 한글 명칭 가져올 수 있음
    // krName 보단 kr, en 이나 krGender, enGender 가 좀 더 명확할 것 같아보임. en도 추가할거지?
    private final String krGender;
    private final String enGender;

    Gender(String krGender, String enGender) {
        this.krGender = krGender;
        this.enGender = enGender;
    }

    public String getKrName() {
        return krGender;
    }

    public String getEnGender() {
        return enGender;
    }
}
