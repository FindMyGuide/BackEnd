package com.findMyGuide.member.domain.entity;

public enum Gender {

    Male ("남"),
    Female ("여");

    //front에서 값 출력시 값.krName으로 한글 명칭 가져올 수 있음
    private final String krName;

    Gender(String krName) {
        this.krName = krName;
    }

    public String getKrName() {
        return krName;
    }
}
