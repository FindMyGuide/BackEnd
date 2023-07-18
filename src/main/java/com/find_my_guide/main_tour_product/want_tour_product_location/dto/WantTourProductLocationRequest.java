package com.find_my_guide.main_tour_product.want_tour_product_location.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class WantTourProductLocationRequest {

    private Long locationId;
    private Long wantTourProductId;
}
