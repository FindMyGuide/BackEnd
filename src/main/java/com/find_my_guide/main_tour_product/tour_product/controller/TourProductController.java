package com.find_my_guide.main_tour_product.tour_product.controller;

import com.find_my_guide.main_tour_product.available_reservation_date.service.AvailableService;
import com.find_my_guide.main_tour_product.tour_product.service.TourProductService;
import com.find_my_guide.main_tour_product.tour_product.dto.TourProductRequest;
import com.find_my_guide.main_tour_product.tour_product.dto.TourProductResponse;
import com.find_my_guide.main_tour_product.tour_product_theme.service.TourProductThemeService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api")
@Api
public class TourProductController {

    private final TourProductService tourProductService;


    @PostMapping("/tourProduct/register")
    public ResponseEntity<TourProductResponse> addTourProduct(
            final Authentication authentication,
            @RequestBody TourProductRequest tourProductRequest) {


        TourProductResponse tourProductResponse =
                tourProductService.registerTourProduct((String) authentication.getPrincipal(), tourProductRequest);

        log.info("test log {}", tourProductResponse.getLocations());

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

    @GetMapping("/tourProducts/search")
    public ResponseEntity<List<TourProductResponse>> findByLocation(@RequestParam String title){
        return ResponseEntity.ok(tourProductService.getTourProductsByLocationTitle(title));
    }


}
