package com.find_my_guide.main_tour_product.tour_product_review.service;

import com.find_my_guide.main_member.member.domain.entity.Member;
import com.find_my_guide.main_member.member.repository.MemberRepository;
import com.find_my_guide.main_tour_product.common.validation_field.Content;
import com.find_my_guide.main_tour_product.tour_product.domain.TourProduct;
import com.find_my_guide.main_tour_product.tour_product.repository.TourProductRepository;
import com.find_my_guide.main_tour_product.tour_product_review.domain.Rating;
import com.find_my_guide.main_tour_product.tour_product_review.domain.TourProductReview;
import com.find_my_guide.main_tour_product.tour_product_review.dto.TourProductReviewRequest;
import com.find_my_guide.main_tour_product.tour_product_review.dto.TourProductReviewResponse;
import com.find_my_guide.main_tour_product.tour_product_review.repository.TourProductReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TourProductReviewService {

    private final TourProductReviewRepository tourProductReviewRepository;

    private final TourProductRepository tourProductRepository;

    private final MemberRepository memberRepository;

    @Transactional
    public TourProductReviewResponse register(Long postId, String email, TourProductReviewRequest tourProductReviewRequest) {
        TourProduct tourProduct = findTourProductById(postId);

        Member member = memberRepository.findByEmail(email).orElseThrow(() -> new NoSuchElementException());



        TourProductReview tourProductReview = tourProductReviewRequest.toTourProductReview();
        tourProductReview.setMember(member);


        isSameReview(tourProduct.getTourProductReviews().contains(tourProductReview));

        tourProductReview.addReview(tourProduct);


        return new TourProductReviewResponse(tourProductReviewRepository.save(tourProductReview));
    }


    @Transactional
    public TourProductReviewResponse update(Long postId, Long reviewId, TourProductReviewRequest tourProductReviewRequest) {
        findTourProductById(postId);

        TourProductReview review = findReviewById(reviewId);

        isMatchTourProductIdReviewId(postId, review);

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
        return findTourProductById(postId).getTourProductReviews().stream()
                .map(TourProductReviewResponse::new)
                .collect(Collectors.toList());

    }

    public List<TourProductReviewResponse> findLatestReviewsByTourProduct(Long postId) {
        TourProduct tourProduct = findTourProductById(postId);

        List<TourProductReview> reviews = tourProductReviewRepository.findByTourProductOrderByCreatedAtDesc(tourProduct, PageRequest.of(0, 10));

        return reviews.stream()
                .map(TourProductReviewResponse::new)
                .collect(Collectors.toList());
    }


    private void isMatchTourProductIdReviewId(Long postId, TourProductReview review) {
        isSameReview(!review.getTourProduct().getTourProductId().equals(postId));
    }

    private TourProductReview findReviewById(Long reviewId) {
        return tourProductReviewRepository.findById(reviewId)
                .orElseThrow(() -> new IllegalArgumentException("Review does not exist"));
    }


    private TourProduct findTourProductById(Long id) {
        return tourProductRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("존재 하지 않는 관광 상품 입니다."));
    }

    private void isSameReview(boolean tourProduct) {
        if (tourProduct) {
            throw new IllegalArgumentException("이미_같은_리뷰가_존재함");
        }
    }
}
