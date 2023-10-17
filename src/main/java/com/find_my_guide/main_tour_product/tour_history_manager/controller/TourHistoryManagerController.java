package com.find_my_guide.main_tour_product.tour_history_manager.controller;

import com.amazonaws.Response;
import com.find_my_guide.main_tour_product.tour_history_manager.dto.TourHistoryManagerResponse;
import com.find_my_guide.main_tour_product.tour_history_manager.dto.TourHistoryTouristRequest;
import com.find_my_guide.main_tour_product.tour_history_manager.dto.WantTourHistoryManagerResponse;
import com.find_my_guide.main_tour_product.tour_history_manager.service.TourHistoryManagerService;
import com.find_my_guide.main_tour_product.tour_product.dto.TourProductResponse;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    //가이드가 투어 예약
    @PostMapping("/reserve-tour/by-guide/{wantTourProductId}")
    public ResponseEntity<TourHistoryManagerResponse> reservedByGuide(
            @PathVariable Long wantTourProductId,
            final Authentication authentication
    ) {
        return ResponseEntity.ok(tourHistoryManagerService.registerWantTourHistoryByGuide((String) authentication
                .getPrincipal(), wantTourProductId));
    }

    @GetMapping("/all-reserved-tour/by-guide")
    public ResponseEntity<List<TourHistoryManagerResponse>> allReservedTourProducts(
            final Authentication authentication
    ){
        return ResponseEntity.ok(tourHistoryManagerService.findAllReservedTourProductByGuide((String) authentication.getPrincipal()));

    }
    @GetMapping("/all-reserved-wantTour/by-guide")
    public ResponseEntity<List<WantTourHistoryManagerResponse>> allReservedWantTourProducts(
            final Authentication authentication
    ){
        return ResponseEntity.ok(tourHistoryManagerService.findAllReservedWantTourProductByGuide((String)authentication.getPrincipal() ));
    }


    @GetMapping("/top-10")
    public ResponseEntity<List<TourProductResponse>> top10() {
        return ResponseEntity.ok(tourHistoryManagerService.getTop10TourProductsByFrequency());
    }


    @PostMapping("/delete-reserved-tour/{tourHistoryManagerId}")
    public ResponseEntity<?> deleteReservedTour(
            @PathVariable Long tourHistoryManagerId,
            final Authentication authentication
    ) {
        try {
            tourHistoryManagerService.cancelReservation((String)authentication.getPrincipal(), tourHistoryManagerId);
            return ResponseEntity.ok("예약이 성공적으로 취소되었습니다.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("예약 취소 중 오류가 발생했습니다.");
        }
    }

}
