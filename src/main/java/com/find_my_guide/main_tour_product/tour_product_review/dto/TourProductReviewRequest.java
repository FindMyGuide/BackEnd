package com.find_my_guide.main_tour_product.tour_product_review.dto;

import com.find_my_guide.main_tour_product.common.validation_field.Content;
import com.find_my_guide.main_tour_product.tour_product_review.domain.Rating;
import com.find_my_guide.main_tour_product.tour_product_review.domain.TourProductReview;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.DecimalMax;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TourProductReviewRequest {

    private String content;


    @DecimalMin(value = "0.0", inclusive = true)
    @DecimalMax(value = "5.0", inclusive = true)
    private double rating;

    private String imageUrl;


    public TourProductReview toTourProductReview(){
        return TourProductReview.builder()
                .content(new Content(content))
                .rating(new Rating(rating))
                .imageUrl(imageUrl)
                .build();
    }
}
