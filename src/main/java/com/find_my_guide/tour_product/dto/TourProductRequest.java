package com.find_my_guide.tour_product.dto;

import com.find_my_guide.common.validation_field.Content;
import com.find_my_guide.common.validation_field.Title;
import com.find_my_guide.tour_product.domain.TourProduct;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TourProductRequest {

    private Long tourProductId;

    private String title;

    private String content;

    public TourProduct toTourProduct() {
        return TourProduct.builder()
                .tourProductId(tourProductId)
                .title(new Title(title))
                .content(new Content(content))
                .build();
    }

}
