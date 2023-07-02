package com.find_my_guide.tour_product.service;

import com.find_my_guide.common.validation_field.Content;
import com.find_my_guide.common.validation_field.Title;
import com.find_my_guide.tour_product.domain.TourProduct;
import com.find_my_guide.tour_product.repository.TourProductRepository;
import com.find_my_guide.tour_product_review.domain.TourProductReview;
import com.find_my_guide.tour_product_review.dto.TourProductReviewRequest;
import com.find_my_guide.tour_product_review.dto.TourProductReviewResponse;
import com.find_my_guide.tour_product_review.repository.TourProductReviewRepository;
import com.find_my_guide.tour_product_review.service.TourProductReviewService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TourProductServiceTest {

    @InjectMocks
    TourProductService tourProductService;

    @Mock
    TourProductRepository tourProductRepository;

    @Mock
    TourProductReviewRepository tourProductReviewRepository;

    @Mock
    TourProductReviewService tourProductReviewService;

    @Test
    @DisplayName("관광 투어 저장")
    void register() {


        // Given
        TourProduct tourProduct = TourProduct.builder().
                tourProductId(1L)
                .title(new Title("hi"))
                .content(new Content("cotnest"))
                .build();

        when(tourProductRepository.save(any(TourProduct.class))).thenReturn(tourProduct);

        // When
        TourProduct savedTourProduct = tourProductRepository.save(tourProduct);

        // Then
        Assertions.assertNotNull(savedTourProduct.getTourProductId());
        Assertions.assertEquals(tourProduct.getTitle(), savedTourProduct.getTitle());
    }


    @Test
    @DisplayName("리뷰 저장")
    void registerReview() {
        Long postId = 1L;
        TourProductReviewRequest reviewRequest = new TourProductReviewRequest(1L,"hi");


        TourProduct product = TourProduct.builder().
                tourProductId(1L)
                .title(new Title("hi"))
                .content(new Content("cotnest"))
                .build();


        TourProductReview review = reviewRequest.toTourProductReview();
        TourProductReviewResponse reviewResponse = new TourProductReviewResponse(review);
        when(tourProductReviewService.register(eq(postId), any(TourProductReviewRequest.class))).thenReturn(reviewResponse);

        // When
        TourProductReviewResponse savedReviewResponse = tourProductReviewService.register(postId, reviewRequest);

        // Then
        Assertions.assertNotNull(savedReviewResponse);
        Assertions.assertEquals(reviewRequest.getContent(), savedReviewResponse.getContent());

    }
}