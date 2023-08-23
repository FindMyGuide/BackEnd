package com.find_my_guide.main_tour_product.tour_product.controller;

import com.find_my_guide.main_tour_product.available_reservation_date.service.AvailableService;
import com.find_my_guide.main_tour_product.tour_product.service.TourProductService;
import com.find_my_guide.main_tour_product.tour_product.dto.TourProductRequest;
import com.find_my_guide.main_tour_product.tour_product.dto.TourProductResponse;
import com.find_my_guide.main_tour_product.tour_product_theme.service.TourProductThemeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class TourProductController {

    private final TourProductService tourProductService;


    @PostMapping("/tourProduct/register/{memberId}")
    public ResponseEntity<TourProductResponse> addTourProduct(
            @PathVariable Long memberId,
            @RequestBody TourProductRequest tourProductRequest) {
        TourProductResponse tourProductResponse =
                tourProductService.registerTourProduct(memberId, tourProductRequest);
        return ResponseEntity.ok(tourProductResponse);
    }

    @GetMapping("/tourProduct/{tourProductId}")
    public ResponseEntity<TourProductResponse> detail(@Valid @PathVariable Long tourProductId) {
        TourProductResponse tourProductResponse = tourProductService.detail(tourProductId);
        return ResponseEntity.ok(tourProductResponse);
    }

    @GetMapping("/tourProducts")
    public ResponseEntity<List<TourProductResponse>> showTourProductList() {
        List<TourProductResponse> tourProductResponses = tourProductService.showTourProductList();
        return ResponseEntity.ok(tourProductResponses);
    }

    @GetMapping("/tourProducts/{tourProductId}/likes")
    public Long showTourProductLikes(@PathVariable Long tourProductId) {
        long counts = tourProductService.countLikes(tourProductId);
        return counts;
    }


}
