package com.find_my_guide.main_tour_product.want_reservation_date.controller;

import com.find_my_guide.main_tour_product.want_reservation_date.dto.WantReservationDateRequest;
import com.find_my_guide.main_tour_product.want_reservation_date.dto.WantReservationDateResponse;
import com.find_my_guide.main_tour_product.want_reservation_date.service.WantReservationDateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class WantReservationDateController {

    private final WantReservationDateService wantReservationDateService;

    @PostMapping("/wantTourProduct/registerDate/{wantTourProductId}")
    public ResponseEntity<WantReservationDateResponse> registerDate(
            @Valid @PathVariable Long wantTourProductId,
            @RequestBody WantReservationDateRequest wantReservationDateRequest
            ) {
        WantReservationDateResponse wantReservationDateResponse =
                wantReservationDateService.registerDate(wantTourProductId, wantReservationDateRequest);
        return ResponseEntity.ok(wantReservationDateResponse);
    }
}
