package com.find_my_guide.main_tour_product.available_reservation_date.controller;


import com.find_my_guide.main_tour_product.available_reservation_date.dto.AvailableDateRequest;
import com.find_my_guide.main_tour_product.available_reservation_date.dto.AvailableDateResponse;
import com.find_my_guide.main_tour_product.available_reservation_date.service.AvailableService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class AvailableDateController {

    private final AvailableService availableService;

    @PostMapping("/postTourProduct/registerDate/{tourProductId}")
    public ResponseEntity<AvailableDateResponse> registerDate(@Valid @PathVariable Long tourProductId,
                                                              @RequestBody AvailableDateRequest availableDateRequest) {
        AvailableDateResponse availableDateResponse = availableService.registerDate(tourProductId, availableDateRequest);
        return ResponseEntity.ok(availableDateResponse);
    }
}
