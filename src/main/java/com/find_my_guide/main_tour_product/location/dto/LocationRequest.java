package com.find_my_guide.main_tour_product.location.dto;

import com.find_my_guide.main_tour_product.common.validation_field.Title;
import com.find_my_guide.main_tour_product.location.domain.Location;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class LocationRequest {

    private Long id;
    private String title;
    private BigDecimal mapX;
    private BigDecimal mapY;

    public Location toSelfTourLocation() {
        return Location.builder()
                .locationId(id)
                .title(new Title(title))
                .mapX(mapX)
                .mapY(mapY)
                .build();
    }

    public LocationRequest(String title, BigDecimal mapX, BigDecimal mapY) {
        this.title = title;
        this.mapX = mapX;
        this.mapY = mapY;
    }
}
