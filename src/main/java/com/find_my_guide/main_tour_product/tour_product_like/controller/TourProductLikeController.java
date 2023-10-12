package com.find_my_guide.main_tour_product.tour_product_like.controller;

import com.find_my_guide.main_tour_product.tour_product_like.dto.TourProductLikeRequest;
import com.find_my_guide.main_tour_product.tour_product_like.dto.TourProductLikeResponse;
import com.find_my_guide.main_tour_product.tour_product_like.service.TourProductLikeService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Api
public class TourProductLikeController {

    private final TourProductLikeService tourProductLikeService;

    @PostMapping("/tourProduct/like")
    public ResponseEntity<TourProductLikeResponse> addLike(@RequestParam Long tourProductId, final Authentication authentication){

        String email = (String) authentication.getPrincipal();

        TourProductLikeRequest tourProductLikeRequest = new TourProductLikeRequest();
        tourProductLikeRequest.setTourProductId(tourProductId);
        tourProductLikeRequest.setEmail(email);
        return ResponseEntity.ok(tourProductLikeService.addLike(tourProductLikeRequest));
    }

    @DeleteMapping("/tourProduct/delete-like")
    public ResponseEntity<Void> removeLike(@RequestParam Long tourProductId, final Authentication authentication){
        String email = (String) authentication.getPrincipal();

        TourProductLikeRequest tourProductLikeRequest = new TourProductLikeRequest();
        tourProductLikeRequest.setTourProductId(tourProductId);
        tourProductLikeRequest.setEmail(email);

        tourProductLikeService.removeLike(tourProductLikeRequest);
        return ResponseEntity.noContent().build();
    }

}
