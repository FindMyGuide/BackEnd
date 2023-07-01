package com.find_my_guide.tour_product_review.dto;

import com.find_my_guide.common.validation_field.Content;
import com.find_my_guide.tour_product_review.domain.TourProductReview;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TourProductReviewRequest {

    private Long id;

    private String content;



    public TourProductReview toTourProductReview(){
        return TourProductReview.builder()
                .id(id)
                .content(new Content(content))
                .build();
    }
}
