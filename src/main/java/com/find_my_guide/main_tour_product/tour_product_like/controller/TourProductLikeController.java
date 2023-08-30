package com.find_my_guide.main_tour_product.tour_product_like.controller;

import com.find_my_guide.main_tour_product.tour_product_like.dto.TourProductLikeRequest;
import com.find_my_guide.main_tour_product.tour_product_like.dto.TourProductLikeResponse;
import com.find_my_guide.main_tour_product.tour_product_like.service.TourProductLikeService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Api
public class TourProductLikeController {

    private final TourProductLikeService tourProductLikeService;

    @PostMapping("/tourProduct/like")
    public ResponseEntity<TourProductLikeResponse> addLike(@RequestBody TourProductLikeRequest requestDto){
        return ResponseEntity.ok(tourProductLikeService.addLike(requestDto));
    }
}
