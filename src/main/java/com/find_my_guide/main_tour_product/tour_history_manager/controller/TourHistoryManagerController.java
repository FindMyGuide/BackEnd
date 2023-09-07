//package com.find_my_guide.main_tour_product.tour_history_manager.controller;
//
//import com.find_my_guide.main_tour_product.tour_history_manager.dto.TourHistoryGuideRequest;
//import com.find_my_guide.main_tour_product.tour_history_manager.dto.TourHistoryManagerGuideRequest;
//import com.find_my_guide.main_tour_product.tour_history_manager.dto.TourHistoryManagerResponse;
//import com.find_my_guide.main_tour_product.tour_history_manager.service.TourHistoryManagerService;
//import io.swagger.annotations.Api;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequiredArgsConstructor
//@RequestMapping("/api/tourProduct")
//@Api
//public class TourHistoryManagerController {
//
//    private final TourHistoryManagerService tourHistoryManagerService;
//
//
//    // 가이드가 투어 상품 등록
//    @PostMapping("/register-by-guide")
//    public ResponseEntity<TourHistoryManagerResponse> registerByGuide(@RequestBody TourHistoryGuideRequest tourHistoryGuideRequest) {
//        TourHistoryManagerResponse response = tourHistoryManagerService.registerTourProductByGuide(tourHistoryGuideRequest.getEmail(), tourHistoryGuideRequest.getProductId());
//        return ResponseEntity.ok(response);
//    }
//
//    // 여행객이 투어 예약
//    @PostMapping("/reserve-tour/{tourProductId}")
//    public ResponseEntity<TourHistoryManagerResponse> reserveByTouristUsingProductId(
//            @RequestParam String touristEmail,
//            @PathVariable Long tourProductId,
//            @RequestBody TourHistoryManagerGuideRequest request) {
//
//        TourHistoryManagerResponse response = tourHistoryManagerService.reserveTourByTourist(touristEmail, tourProductId, request);
//        return ResponseEntity.ok(response);
//    }
//
//
//}
