package com.find_my_guide.main_tour_product.tour_product_review.dto;


import com.find_my_guide.main_tour_product.tour_product_review.domain.TourProductReview;
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


    private String tourTitle;


    public TourProductReviewResponse(TourProductReview tourProductReview) {
        this.id = tourProductReview.getId();
        this.content = tourProductReview.getContent() != null ? tourProductReview.getContent().getContent() : null;
        this.rating = tourProductReview.getRating() != null ? tourProductReview.getRating().getRating() : null;
        this.imageUrl = tourProductReview.getImageUrl() != null ? tourProductReview.getImageUrl() : null;
        this.tourTitle = tourProductReview.getTourProduct() != null ? tourProductReview.getTourProduct().getTitle().getTitle() : null;
    }
}
