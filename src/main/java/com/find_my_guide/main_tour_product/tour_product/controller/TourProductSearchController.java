package com.find_my_guide.main_tour_product.tour_product.controller;

import com.find_my_guide.main_tour_product.tour_product.dto.TourProductResponse;
import com.find_my_guide.main_tour_product.tour_product.service.TourProductService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Api
public class TourProductSearchController {

    private final TourProductService tourProductService;


    @GetMapping("/tourProducts/likes")
    public ResponseEntity<List<TourProductResponse>> showTourProductList() {
        List<TourProductResponse> tourProductResponses = tourProductService.showTourProductListInOrderLikes();
        return ResponseEntity.ok(tourProductResponses);
    }

}
