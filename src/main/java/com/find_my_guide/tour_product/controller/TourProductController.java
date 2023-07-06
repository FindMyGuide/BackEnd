package com.find_my_guide.tour_product.controller;

import com.find_my_guide.tour_product.dto.TourProductRequest;
import com.find_my_guide.tour_product.dto.TourProductResponse;
import com.find_my_guide.tour_product.service.TourProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class TourProductController {

    private final TourProductService tourProductService;

    @PostMapping("/post")
    public ResponseEntity<TourProductResponse> register(@Valid @RequestBody TourProductRequest tourProductRequest) {
        TourProductResponse tourProductResponse = tourProductService.register(tourProductRequest);
        return ResponseEntity.ok(tourProductResponse);
    }

    @GetMapping("/tourProduct/{tourProductId}")
    public ResponseEntity<TourProductResponse> detail(@Valid @PathVariable Long tourProductId){
        TourProductResponse tourProductResponse = tourProductService.detail(tourProductId);
        return ResponseEntity.ok(tourProductResponse);
    }

    @GetMapping("/tourProducts")
    public ResponseEntity<List<TourProductResponse>> showTourProductList() {
        List<TourProductResponse> tourProductResponses = tourProductService.showTourProductList();
        return ResponseEntity.ok(tourProductResponses);
    }


}
