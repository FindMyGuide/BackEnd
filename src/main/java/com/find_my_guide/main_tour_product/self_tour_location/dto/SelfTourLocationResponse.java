package com.find_my_guide.main_tour_product.self_tour_location.dto;

import com.find_my_guide.main_tour_product.common.validation_field.Title;
import com.find_my_guide.main_tour_product.self_tour_location.domain.SelfTourLocation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SelfTourLocationResponse {

    private String title;
    private String mapX;
    private String mapY;

    public SelfTourLocationResponse(SelfTourLocation selfTourLocation) {
        this.title = selfTourLocation.getTitle().getTitle();
        this.mapX = selfTourLocation.getMapX();
        this.mapY = selfTourLocation.getMapY();
    }
}
