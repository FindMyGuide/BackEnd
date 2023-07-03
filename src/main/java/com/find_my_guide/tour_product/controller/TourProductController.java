package com.find_my_guide.tour_product.controller;

import com.find_my_guide.tour_product.dto.TourProductRequest;
import com.find_my_guide.tour_product.dto.TourProductResponse;
import com.find_my_guide.tour_product.service.TourProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class TourProductController {

    private final TourProductService tourProductService;

    @PostMapping("/post")
    public ResponseEntity<TourProductResponse> register(@Valid @RequestBody TourProductRequest tourProductRequest) {
        TourProductResponse tourProductResponse = tourProductService.register(tourProductRequest);
        return ResponseEntity.ok(tourProductResponse);
    }


}
