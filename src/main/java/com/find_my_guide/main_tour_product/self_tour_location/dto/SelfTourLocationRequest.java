package com.find_my_guide.main_tour_product.self_tour_location.dto;

import com.find_my_guide.main_tour_product.common.validation_field.Title;
import com.find_my_guide.main_tour_product.self_tour_location.domain.SelfTourLocation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class SelfTourLocationRequest {

    private Long id;
    private String title;
    private String mapX;
    private String mapY;

    public SelfTourLocation toSelfTourLocation() {
        return SelfTourLocation.builder()
                .selfTourLocationId(id)
                .title(new Title(title))
                .mapX(mapX)
                .mapY(mapY)
                .build();
    }
}
