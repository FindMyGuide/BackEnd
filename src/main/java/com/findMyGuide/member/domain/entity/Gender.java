package com.findMyGuide.member.domain.entity;

public enum Gender {

    Male ("남"),
    Female ("여");

    //front에서 값 출력시 값.krName으로 한글 명칭 가져올 수 있음
    // krName 보단 kr, en 이나 krGender, enGender 가 좀 더 명확할 것 같아보임. en도 추가할거지?
    // 6.30(금) 이현호 작성
    // enGender는 기본 값으로 넘어오는거라 .name()으로 가져올 수 있어요. 그래서 따로 안만들어도 될거 같아요
    private final String krGender;

    Gender(String krGender) {
        this.krGender = krGender;
    }

    public String getKrName() {
        return krGender;
    }
}
