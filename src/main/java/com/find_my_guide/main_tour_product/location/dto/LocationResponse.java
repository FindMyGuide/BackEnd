package com.find_my_guide.main_tour_product.location.dto;

import com.find_my_guide.main_tour_product.location.domain.Location;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LocationResponse {

    private String title;
    private BigDecimal mapX;
    private BigDecimal mapY;

    public LocationResponse(Location location) {
        if (location != null) {
            if (location.getTitle() != null) {
                this.title = location.getTitle().getTitle();
            }
            if (location.getCoordinates() != null) {
                this.mapX = location.getCoordinates().getMapX();
                this.mapY = location.getCoordinates().getMapY();
            }
        }
    }

}
