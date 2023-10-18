package com.find_my_guide.main_tour_product.tour_product_location.dto;

import com.find_my_guide.main_tour_product.common.validation_field.Title;
import com.find_my_guide.main_tour_product.tour_product_location.domain.TourProductLocation;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Optional;

@Data
public class TourProductLocationResponse {

    private String title;

    private String date;
    private BigDecimal mapX;

    private BigDecimal mapY;

    public TourProductLocationResponse(TourProductLocation tourProductLocation) {

        if (tourProductLocation != null && tourProductLocation.getLocation() != null) {
            this.title = Optional.ofNullable(tourProductLocation.getLocation().getTitle())
                    .map(Title::getTitle)
                    .orElse("");

            this.date = Optional.ofNullable(tourProductLocation.getLocation().getDate()).orElse(null);

            if (tourProductLocation.getLocation().getCoordinates() != null) {
                this.mapX = tourProductLocation.getLocation().getCoordinates().getMapX();
                this.mapY = tourProductLocation.getLocation().getCoordinates().getMapY();
            } else {
                this.mapX = null;
                this.mapY = null;
            }
        } else {
            this.title = "";
            this.date = null;
            this.mapX = null;
            this.mapY = null;
        }

    }
}
