package com.find_my_guide.main_tour_product.tour_product.dto;

import com.find_my_guide.main_tour_product.tour_product.domain.TourProduct;
import com.find_my_guide.main_tour_product.tour_product_location.dto.TourProductLocationResponse;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class TourProductSearchResponse {

    private Long id;

    private String title;

    private String content;

    private List<TourProductLocationResponse> locations;

    public TourProductSearchResponse(TourProduct tourProduct) {

        this.id = tourProduct.getTourProductId();
        this.title = tourProduct.getTitle().getTitle();
        this.content = tourProduct.getContent().getContent();


        this.locations = tourProduct.getTourProductLocations().
                stream().map(TourProductLocationResponse::new)
                .collect(Collectors.toList());
    }
}
