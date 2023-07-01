package com.find_my_guide.tour_product.service;

import com.find_my_guide.common.validation_field.Content;
import com.find_my_guide.tour_product.domain.TourProduct;
import com.find_my_guide.tour_product.repository.TourProductRepository;
import com.find_my_guide.tour_product_review.domain.TourProductReview;
import com.find_my_guide.tour_product_review.dto.TourProductReviewRequest;
import com.find_my_guide.tour_product_review.service.TourProductReviewService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@SpringBootTest
public class TourProductServiceTest {

    @Autowired
    private TourProductService tourProductService;

    @Autowired
    TourProductRepository tourProductRepository;

    @Autowired
    TourProductReviewService tourProductReviewService;

    @Test
    @DisplayName("관광 투어 저장")
    void register() {


        tourProductReviewService.register(1L, new TourProductReviewRequest(2L, "new Review"));


    }

    @Test
    @DisplayName("리뷰 찾기")
    @Transactional
    void showReview() {

        Optional<TourProduct> byId = tourProductRepository.findById(1L);
        List<TourProductReview> tourProductReviews = byId.get().getTourProductReviews();
        for (TourProductReview tourProductReview : tourProductReviews) {
            System.out.println("asd   " + tourProductReview.getContent().getContent());
        }
    }
}