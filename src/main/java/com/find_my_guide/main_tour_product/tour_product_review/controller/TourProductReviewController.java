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
import org.springframework.web.multipart.MultipartFile;

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
            @RequestPart(required=false) MultipartFile file,
            @RequestPart TourProductReviewRequest tourProductReviewRequest) {
        return ResponseEntity.ok(tourProductReviewService.register(postId,(String) authentication.getPrincipal(), tourProductReviewRequest,
                file));
    }

    @GetMapping("/all/{postId}")
    public ResponseEntity<List<TourProductReviewResponse>> findAll(
            @PathVariable Long postId
    ){

        return ResponseEntity.ok(tourProductReviewService.reviewList(postId));
    }

    @GetMapping("/recent-reviews")
    public ResponseEntity<List<TourProductReviewResponse>> showRecentReviews() {
        return ResponseEntity.ok(tourProductReviewService.findLatest10Reviews());
    }

    @GetMapping("/all/by-member")
    public ResponseEntity<List<TourProductReviewResponse>> showAllReviews(
            final Authentication authentication
    ){
        return ResponseEntity.ok(tourProductReviewService.findAllByMember((String) authentication.getPrincipal()));
    }




}
