package com.find_my_guide.tour_product.dto;

import com.find_my_guide.tour_product.domain.TourProduct;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TourProductResponse {

    private Long id;

    private String title;

    private String content;

    private BigDecimal price;



    public TourProductResponse(TourProduct tourProduct) {
        this.id = tourProduct.getTourProductId();
        this.title = tourProduct.getTitle().getTitle();
        this.content = tourProduct.getContent().getContent();
        this.price = BigDecimal.valueOf(tourProduct.getPrice().getPrice().longValue());
    }
}
