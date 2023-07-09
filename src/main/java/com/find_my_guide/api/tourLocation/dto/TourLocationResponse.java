package com.find_my_guide.api.tourLocation.dto;

import com.find_my_guide.api.tourLocation.domain.TourLocation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class TourLocationResponse {

    private Long id;
    private String title;
    private String image;
    private String mapx;
    private String mapy;

    public TourLocationResponse(TourLocation tourLocation) {
        this.id = tourLocation.getId();
        this.title = tourLocation.getTitle();
        this.image = tourLocation.getImage();
        this.mapx = tourLocation.getMapx();
        this.mapy = tourLocation.getMapy();
    }
}