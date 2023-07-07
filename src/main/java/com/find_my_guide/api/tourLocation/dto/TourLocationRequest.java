package com.find_my_guide.api.tourLocation.dto;

import com.find_my_guide.api.tourLocation.domain.TourLocation;
import com.find_my_guide.api.tourLocationDetail.domain.TourLocationDetail;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class TourLocationRequest {

    private Long id;
    private String title;
    private String image;
    private String mapx;
    private String mapy;

    public TourLocation toTourLocation() {

        return TourLocation.builder()
                .id(this.id)
                .title(this.title)
                .image(this.image)
                .mapx(this.mapx)
                .mapy(this.mapy)
                .build();
    }


}