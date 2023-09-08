package com.find_my_guide.main_tour_product.tour_history_manager.controller;

import com.find_my_guide.main_tour_product.tour_history_manager.dto.TourHistoryManagerGuideRequest;
import com.find_my_guide.main_tour_product.tour_history_manager.dto.TourHistoryManagerResponse;
import com.find_my_guide.main_tour_product.tour_history_manager.dto.TourHistoryTouristRequest;
import com.find_my_guide.main_tour_product.tour_history_manager.service.TourHistoryManagerService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/tourProduct")
@Api
public class TourHistoryManagerController {

    private final TourHistoryManagerService tourHistoryManagerService;


    // 여행객이 투어 예약
    @PostMapping("/reserve-tour/{tourProductId}")
    public ResponseEntity<TourHistoryManagerResponse> reserveByTouristUsingProductId(
            @PathVariable Long tourProductId,
            @RequestBody TourHistoryTouristRequest request,
            final Authentication authentication) {
        request.setProductId(tourProductId);
        request.setEmail((String) authentication.getPrincipal());

        return ResponseEntity.ok(tourHistoryManagerService.reserveTourByTourist(request));
    }


}
