package com.find_my_guide.main_tour_product.tour_product_location.dto;

import com.find_my_guide.main_tour_product.tour_product_location.domain.TourProductLocation;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class TourProductLocationResponse {

    private String title;

    private BigDecimal mapX;

    private BigDecimal mapY;

    public TourProductLocationResponse(TourProductLocation tourProductLocation) {

        this.title = tourProductLocation.getLocation().getTitle().getTitle();
        this.mapX = tourProductLocation.getLocation().getCoordinates().getMapX();
        this.mapY = tourProductLocation.getLocation().getCoordinates().getMapY();


    }
}
