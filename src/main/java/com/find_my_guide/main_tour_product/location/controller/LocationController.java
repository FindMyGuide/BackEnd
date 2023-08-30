package com.find_my_guide.main_tour_product.location.controller;

import com.find_my_guide.main_tour_product.location.dto.LocationRequest;
import com.find_my_guide.main_tour_product.location.dto.LocationResponse;
import com.find_my_guide.main_tour_product.location.service.LocationService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Api
public class LocationController {

    private final LocationService locationService;

    @PostMapping("/location")
    public ResponseEntity<LocationResponse> addLocation(@RequestBody LocationRequest locationRequest) {
        LocationResponse locationResponse = locationService.register(locationRequest);
        return ResponseEntity.ok(locationResponse);
    }
}
