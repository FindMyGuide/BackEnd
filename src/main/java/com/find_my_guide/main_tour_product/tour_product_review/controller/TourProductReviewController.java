package com.find_my_guide.main_tour_product.tour_product_review.controller;


import com.find_my_guide.main_tour_product.tour_product_review.dto.TourProductReviewRequest;
import com.find_my_guide.main_tour_product.tour_product_review.dto.TourProductReviewResponse;
import com.find_my_guide.main_tour_product.tour_product_review.service.TourProductReviewService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/tour-product-review")
@Api
public class TourProductReviewController {


    private final TourProductReviewService tourProductReviewService;

    @PostMapping("/register/{postId}")
    public ResponseEntity<TourProductReviewResponse> register(
            final Authentication authentication,
            @PathVariable Long postId,
            @RequestBody TourProductReviewRequest tourProductReviewRequest) {


        return ResponseEntity.ok(tourProductReviewService.register(postId,(String) authentication.getPrincipal(), tourProductReviewRequest));


    }

    @GetMapping("/all/{postId}")
    public ResponseEntity<List<TourProductReviewResponse>> findAll(
            @PathVariable Long postId
    ){

        return ResponseEntity.ok(tourProductReviewService.reviewList(postId));
    }

    @GetMapping("/recent-reviews/{postId}")
    public ResponseEntity<List<TourProductReviewResponse>> showRecentReviews(
            @PathVariable Long postId
    ) {
        return ResponseEntity.ok(tourProductReviewService.findLatestReviewsByTourProduct(postId));
    }




}
