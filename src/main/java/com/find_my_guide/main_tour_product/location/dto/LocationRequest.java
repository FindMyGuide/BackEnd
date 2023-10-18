package com.find_my_guide.main_tour_product.location.dto;

import com.find_my_guide.main_tour_product.common.validation_field.Title;
import com.find_my_guide.main_tour_product.location.domain.Location;
import com.find_my_guide.main_tour_product.tour_product.domain.Coordinates;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LocationRequest {

    private String title;

    private String date;

    private List<BigDecimal> coordinates = new ArrayList<>();  // 변경된 부분

    public Location toLocation() {
        return Location.builder()
                .title(new Title(title))
                .date(this.date)
                .coordinates(new Coordinates(coordinates.get(0), coordinates.get(1)))
                .build();
    }
}

