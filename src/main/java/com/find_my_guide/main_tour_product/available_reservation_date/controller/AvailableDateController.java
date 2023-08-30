package com.find_my_guide.main_tour_product.available_reservation_date.controller;


import com.find_my_guide.main_tour_product.available_reservation_date.dto.AvailableDateRequest;
import com.find_my_guide.main_tour_product.available_reservation_date.dto.AvailableDateResponse;
import com.find_my_guide.main_tour_product.available_reservation_date.service.AvailableService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Api
public class AvailableDateController {

    private final AvailableService availableService;

    @PostMapping("/postTourProduct/registerDate/{tourProductId}")
    public ResponseEntity<AvailableDateResponse> registerDate(@Valid @PathVariable Long tourProductId,
                                                              @RequestBody AvailableDateRequest availableDateRequest) {
        AvailableDateResponse availableDateResponse = availableService.registerDate(tourProductId, availableDateRequest);
        return ResponseEntity.ok(availableDateResponse);
    }
}
