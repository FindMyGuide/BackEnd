package com.find_my_guide.tour_product.service;

import com.find_my_guide.available_reservation_date.domain.AvailableDate;
import com.find_my_guide.common.validation_field.Content;
import com.find_my_guide.common.validation_field.Title;
import com.find_my_guide.tour_product.domain.TourProduct;
import com.find_my_guide.tour_product.repository.TourProductRepository;
import com.find_my_guide.tour_product_review.dto.TourProductReviewRequest;
import com.find_my_guide.tour_product_review.dto.TourProductReviewResponse;
import com.find_my_guide.tour_product_review.service.TourProductReviewService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
class TourProductSpringBootTest {

    @Autowired
    private TourProductRepository tourProductRepository;

    @Autowired
    private TourProductService tourProductService;

    @Autowired
    private TourProductReviewService tourProductReviewService;


    @Test
    @DisplayName("관광상품 등록")
    void register() {

        // Given
        TourProduct tourProduct = TourProduct.builder().
                tourProductId(1L)
                .title(new Title("hi"))
                .content(new Content("cotnest"))
                .build();

        AvailableDate availableDate = AvailableDate.builder().date(LocalDate.now()).build();

        availableDate.addAvailableDate(tourProduct);

        tourProductRepository.save(tourProduct);


    }

    @Test
    @DisplayName("리뷰 등록")
    void registerReviewTest() {


        TourProductReviewRequest reviewRequest = new TourProductReviewRequest(1L, "hi", 5.0, "image");
        TourProductReviewRequest reviewRequest2 = new TourProductReviewRequest(2L, "good", 4.0, "image2");

        tourProductReviewService.register(2L, reviewRequest);
        tourProductReviewService.register(2L, reviewRequest2);

    }

    @Test
    @DisplayName("리뷰 조회")
    void showReviews(){

        List<TourProductReviewResponse> tourProductReviewResponses = tourProductReviewService.reviewList(2L);

        Assertions.assertThat(tourProductReviewResponses.size()).isEqualTo(2);

    }
}
