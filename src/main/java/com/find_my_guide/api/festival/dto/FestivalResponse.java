package com.find_my_guide.api.festival.dto;

import com.find_my_guide.api.festival.domain.Festival;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class FestivalResponse {

    private Long id;
    private String title;
    private String image;
    private String mapx;
    private String mapy;

    public FestivalResponse(Festival festival) {
        this.id = festival.getId();
        this.title = festival.getTitle();
        this.image = festival.getImage();
        this.mapx = festival.getMapx();
        this.mapy = festival.getMapy();
    }
}