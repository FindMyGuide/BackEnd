package com.find_my_guide.main_tour_product.want_tour_product_location.controller;

import com.find_my_guide.main_tour_product.want_tour_product_location.dto.WantTourProductLocationRequest;
import com.find_my_guide.main_tour_product.want_tour_product_location.dto.WantTourProductLocationResponse;
import com.find_my_guide.main_tour_product.want_tour_product_location.service.WantTourProductLocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class WantTourProductLocationController {

    private final WantTourProductLocationService wantTourProductLocationService;

    @PostMapping("/wantTourProduct/registerLocation/{wantTourProductId}")
    public ResponseEntity<WantTourProductLocationResponse> addLocationToWantTourProduct(
            @PathVariable Long wantTourProductId,
            @RequestBody WantTourProductLocationRequest wantTourProductLocationRequest) {
        wantTourProductLocationRequest.setWantTourProductId(wantTourProductId);
        WantTourProductLocationResponse wantTourProductLocationResponse =
                wantTourProductLocationService.addLocationToWantTourProduct(wantTourProductLocationRequest);
        return ResponseEntity.ok(wantTourProductLocationResponse);
    }
}
