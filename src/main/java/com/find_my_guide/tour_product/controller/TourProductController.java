package com.find_my_guide.tour_product.controller;

import com.find_my_guide.available_reservation_date.dto.AvailableDateRequest;
import com.find_my_guide.available_reservation_date.service.AvailableService;
import com.find_my_guide.tour_product.dto.TourProductRequest;
import com.find_my_guide.tour_product.dto.TourProductResponse;
import com.find_my_guide.tour_product.service.TourProductService;
import com.find_my_guide.tour_product_theme.dto.TourProductThemeRequest;
import com.find_my_guide.tour_product_theme.service.TourProductThemeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class TourProductController {

    private final TourProductService tourProductService;
    private final TourProductThemeService tourProductThemeService;
    private final AvailableService availableService;

    @PostMapping("/tourProduct")
    public ResponseEntity<TourProductResponse> addTourProduct(
            @RequestBody TourProductRequest tourProductRequest) {
        TourProductResponse tourProductResponse =
                tourProductService.addTourProduct(tourProductRequest);
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
