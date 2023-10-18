package com.find_my_guide.main_tour_product.tour_product.dto;

import com.find_my_guide.main_member.member.domain.dto.GuideResponse;
import com.find_my_guide.main_tour_product.location.dto.LocationResponse;
import lombok.Data;

import java.util.List;

@Data
public class SearchResponse {

    private List<TourProductSearchResponse> tourProductResponses;



    public SearchResponse(List<TourProductSearchResponse> tourProducts) {
        this.tourProductResponses = tourProducts;
    }

}
