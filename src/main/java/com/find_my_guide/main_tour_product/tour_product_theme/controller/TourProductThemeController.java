package com.find_my_guide.main_tour_product.tour_product_theme.controller;


import com.find_my_guide.main_tour_product.tour_product_theme.dto.TourProductThemeRequest;
import com.find_my_guide.main_tour_product.tour_product_theme.dto.TourProductThemeResponse;
import com.find_my_guide.main_tour_product.tour_product_theme.service.TourProductThemeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TourProductThemeController {

    private final TourProductThemeService tourProductThemeService;

    @PostMapping("/tourProduct/registerTheme/{tourProductId}")
    public ResponseEntity<TourProductThemeResponse> addThemeToTourProduct(
            @PathVariable Long tourProductId,
            @RequestBody TourProductThemeRequest tourProductThemeRequest) {
        tourProductThemeRequest.setTourProductId(tourProductId);
        TourProductThemeResponse tourProductThemeResponse =
                tourProductThemeService.addThemeToTourProduct(tourProductThemeRequest);
        return ResponseEntity.ok(tourProductThemeResponse);
    }
}
