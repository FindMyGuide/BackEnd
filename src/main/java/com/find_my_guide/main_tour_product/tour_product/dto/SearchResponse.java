package com.find_my_guide.main_tour_product.tour_product.dto;

import com.find_my_guide.main_tour_product.location.dto.LocationResponse;
import lombok.Data;

import java.util.List;

@Data
public class SearchResponse {

    private List<TourProductSearchResponse> tourProductResponses;
    private List<LocationResponse> locationResponses;

    public SearchResponse(List<TourProductSearchResponse> tourProducts, List<LocationResponse> locations) {
        this.tourProductResponses = tourProducts;
        this.locationResponses = locations;
    }

}
