package com.findMyGuide.member.domain.entity;

public enum Gender {

    Male ("남"),
    Female ("여");

    //front에서 값 출력시 값.krName으로 한글 명칭 가져올 수 있음
    // krName 보단 kr, en 이나 krGender, enGender 가 좀 더 명확할 것 같아보임. en도 추가할거지?
    private final String krName;

    Gender(String krName) {
        this.krName = krName;
    }

    public String getKrName() {
        return krName;
    }
}
