package com.find_my_guide.main_tour_product.location.controller;

import com.find_my_guide.main_tour_product.location.dto.LocationRequest;
import com.find_my_guide.main_tour_product.location.dto.LocationResponse;
import com.find_my_guide.main_tour_product.location.service.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LocationController {

    private final LocationService locationService;

    @GetMapping("/location")
    public ResponseEntity<LocationResponse> addlocation(@RequestBody LocationRequest locationRequest) {
        LocationResponse locationResponse = locationService.findByXY(locationRequest);
        return ResponseEntity.ok(locationResponse);
    }
}
