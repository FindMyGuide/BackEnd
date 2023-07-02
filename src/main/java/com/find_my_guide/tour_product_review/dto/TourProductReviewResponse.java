package com.find_my_guide.tour_product_review.dto;


import com.find_my_guide.tour_product_review.domain.TourProductReview;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TourProductReviewResponse {

    private Long id;

    private String content;

    private double rating;

    private String imageUrl;


    public TourProductReviewResponse(TourProductReview tourProductReview) {
        this.id = tourProductReview.getId();
        this.content = tourProductReview.getContent().getContent();
        this.rating = tourProductReview.getRating().getRating();
    }
}
