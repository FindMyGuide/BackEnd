package com.find_my_guide.main_tour_product.tour_product_like.dto;

import com.find_my_guide.main_tour_product.tour_product_like.domain.TourProductLike;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TourProductLikeRequest {

    private Long tourProductId;
    public TourProductLike toTourProductLike() {
        return TourProductLike.builder()
                .id(tourProductId)
                .build();
    }
}
