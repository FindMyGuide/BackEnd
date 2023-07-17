package com.find_my_guide.main_tour_product.location.dto;

import com.find_my_guide.main_tour_product.common.validation_field.Title;
import com.find_my_guide.main_tour_product.location.domain.Location;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class LocationRequest {

    private Long id;
    private String title;
    private String mapX;
    private String mapY;

    public Location toSelfTourLocation() {
        return Location.builder()
                .selfTourLocationId(id)
                .title(new Title(title))
                .mapX(mapX)
                .mapY(mapY)
                .build();
    }
}
