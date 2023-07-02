package com.find_my_guide.tour_product_review.service;

import com.find_my_guide.tour_product.domain.TourProduct;
import com.find_my_guide.tour_product.repository.TourProductRepository;
import com.find_my_guide.tour_product_review.domain.TourProductReview;
import com.find_my_guide.tour_product_review.dto.TourProductReviewRequest;
import com.find_my_guide.tour_product_review.dto.TourProductReviewResponse;
import com.find_my_guide.tour_product_review.repository.TourProductReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TourProductReviewService {

    private final TourProductReviewRepository tourProductReviewRepository;

    private final TourProductRepository tourProductRepository;

    @Transactional
    public TourProductReviewResponse register(Long postId, TourProductReviewRequest tourProductReviewRequest) {
        TourProduct tourProduct = findById(postId);

        TourProductReview tourProductReview = tourProductReviewRequest.toTourProductReview();

        if (tourProduct.getTourProductReviews().contains(tourProductReview)) {
            throw new IllegalArgumentException("이미 같은 리뷰가 존재함");
        }

        tourProductReview.addReview(tourProduct);


        return new TourProductReviewResponse(tourProductReviewRepository.save(tourProductReview));
    }


    private TourProduct findById(Long id) {
        return tourProductRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("존재 하지 않는 관광 상품 입니다."));
    }
}
