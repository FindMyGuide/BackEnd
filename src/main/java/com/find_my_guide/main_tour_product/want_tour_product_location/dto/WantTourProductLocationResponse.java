package com.find_my_guide.main_tour_product.want_tour_product_location.dto;

import com.find_my_guide.main_tour_product.want_tour_product_location.domain.WantTourProductLocation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class WantTourProductLocationResponse {

    private Long id;
    private String title;

    public WantTourProductLocationResponse(WantTourProductLocation wantTourProductLocation) {
        this.id = wantTourProductLocation.getId();
        this.title = wantTourProductLocation.getLocation().getTitle().getTitle();
    }

}
