package com.find_my_guide.tour_product_review.service;

import com.find_my_guide.tour_product.service.TourProductService;
import com.find_my_guide.tour_product_review.dto.TourProductReviewRequest;
import com.find_my_guide.tour_product_review.dto.TourProductReviewResponse;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TourProductReviewServiceTest {

    @Autowired
    TourProductReviewService tourProductReviewService;

    @Autowired
    TourProductService tourProductService;

    @Test
    @DisplayName("투어 상품 리뷰 등록")
    void registerReviews() {


        TourProductReviewRequest reviewRequest = new TourProductReviewRequest(1L, "hi", 5.0);
        TourProductReviewRequest reviewRequest2 = new TourProductReviewRequest(2L, "good", 4.0);

        tourProductReviewService.register(1L, reviewRequest);
        tourProductReviewService.register(1L, reviewRequest2);

        List<TourProductReviewResponse> tourProductReviewResponses = tourProductService.reviewList(1L);

        Assertions.assertThat(tourProductReviewResponses.size()).isEqualTo(2);


    }

    @Test
    @DisplayName("투어 상품 리뷰 조회")
    void showReviews() {

        List<TourProductReviewResponse> tourProductReviewResponses = tourProductService.reviewList(1L);

        Assertions.assertThat(tourProductReviewResponses.size()).isEqualTo(2);

    }

}