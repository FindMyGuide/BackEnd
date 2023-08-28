package com.find_my_guide.main_tour_product.tour_product_review.controller;


import com.find_my_guide.main_tour_product.tour_product_review.dto.TourProductReviewRequest;
import com.find_my_guide.main_tour_product.tour_product_review.dto.TourProductReviewResponse;
import com.find_my_guide.main_tour_product.tour_product_review.service.TourProductReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TourProductReviewController {


    private final TourProductReviewService tourProductReviewService;

    @PostMapping("/tour-product-review/register/{postId}")
    public ResponseEntity<TourProductReviewResponse> register(
            final Authentication authentication,
            @PathVariable Long postId,
            @RequestBody TourProductReviewRequest tourProductReviewRequest) {

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String email = userDetails.getUsername();

        return ResponseEntity.ok(tourProductReviewService.register(postId, email, tourProductReviewRequest));


    }


}
