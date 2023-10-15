package com.find_my_guide.main_tour_product.tour_product.domain;

public enum Languages {

    KOREAN("한국어"),
    ENGLISH("영어"),
    SPANISH("스페인어"),
    CHINESE("중국어"),
    JAPANESE("일본어"),
    FRENCH("프랑스어"),
    GERMAN("독일어"),
    RUSSIAN("러시아어"),
    ITALIAN("이탈리아어"),
    PORTUGUESE("포르투갈어");

    private final String koreanName;

    Languages(String koreanName) {
        this.koreanName = koreanName;
    }

    public String getKoreanName() {
        return koreanName;
    }

    public static Languages fromString(String languageName) {
        for (Languages language : Languages.values()) {
            if (language.getKoreanName().equalsIgnoreCase(languageName)) {
                return language;
            }
        }
        throw new IllegalArgumentException("찾을 수 없음"  + languageName);
    }
}

