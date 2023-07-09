package com.find_my_guide.tour_product_review.service;

import com.find_my_guide.common.validation_field.Content;
import com.find_my_guide.tour_product.domain.TourProduct;
import com.find_my_guide.tour_product.repository.TourProductRepository;
import com.find_my_guide.tour_product_review.domain.Rating;
import com.find_my_guide.tour_product_review.domain.TourProductReview;
import com.find_my_guide.tour_product_review.dto.TourProductReviewRequest;
import com.find_my_guide.tour_product_review.dto.TourProductReviewResponse;
import com.find_my_guide.tour_product_review.repository.TourProductReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TourProductReviewService {

    private final TourProductReviewRepository tourProductReviewRepository;

    private final TourProductRepository tourProductRepository;

    @Transactional
    public TourProductReviewResponse register(Long postId, TourProductReviewRequest tourProductReviewRequest) {
        TourProduct tourProduct = findTourProductById(postId);

        TourProductReview tourProductReview = tourProductReviewRequest.toTourProductReview();

        if (tourProduct.getTourProductReviews().contains(tourProductReview)) {
            throw new IllegalArgumentException("이미 같은 리뷰가 존재함");
        }

        tourProductReview.addReview(tourProduct);


        return new TourProductReviewResponse(tourProductReviewRepository.save(tourProductReview));
    }

    @Transactional
    public TourProductReviewResponse update(Long postId, Long reviewId, TourProductReviewRequest tourProductReviewRequest) {
        findTourProductById(postId);

        TourProductReview review = findReviewById(reviewId);

        matchTourProductId_ReviewId(postId, review);

        review.update(new Content(tourProductReviewRequest.getContent())
                , new Rating(tourProductReviewRequest.getRating())
                , tourProductReviewRequest.getImageUrl());


        return new TourProductReviewResponse(tourProductReviewRepository.save(review));


    }


    public Double reviewRatingAverage(Long postId) {
        return reviewList(postId).stream()
                .mapToDouble(TourProductReviewResponse::getRating)
                .average()
                .orElse(0.0);
    }


    public List<TourProductReviewResponse> reviewList(Long postId) {
        List<TourProductReview> tourProductReviews = findTourProductById(postId).getTourProductReviews();

        return tourProductReviews.stream()
                .map(TourProductReviewResponse::new)
                .collect(Collectors.toList());

    }


    private void matchTourProductId_ReviewId(Long postId, TourProductReview review) {
        if (!review.getTourProduct().getTourProductId().equals(postId)) {
            throw new IllegalArgumentException("Review does not belong to the post");
        }
    }

    private TourProductReview findReviewById(Long reviewId) {
        TourProductReview review = tourProductReviewRepository.findById(reviewId)
                .orElseThrow(() -> new IllegalArgumentException("Review does not exist"));
        return review;
    }


    private TourProduct findTourProductById(Long id) {
        return tourProductRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("존재 하지 않는 관광 상품 입니다."));
    }
}
