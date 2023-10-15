package com.find_my_guide.main_tour_product.location.dto;

import com.find_my_guide.main_tour_product.common.validation_field.Title;
import com.find_my_guide.main_tour_product.location.domain.Location;
import com.find_my_guide.main_tour_product.tour_product.domain.Coordinates;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
public class WantTourLocationRequest {

    private String title;


    public Location toLocation() {
        return Location.builder()
                .title(new Title(title))
                .build();
    }
}
