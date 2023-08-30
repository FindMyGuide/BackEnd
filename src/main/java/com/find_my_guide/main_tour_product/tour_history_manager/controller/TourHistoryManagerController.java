package com.find_my_guide.main_tour_product.tour_history_manager.controller;

import com.find_my_guide.main_tour_product.tour_history_manager.dto.TourHistoryManagerRequest;
import com.find_my_guide.main_tour_product.tour_history_manager.dto.TourHistoryManagerResponse;
import com.find_my_guide.main_tour_product.tour_history_manager.service.TourHistoryManagerService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Api
public class TourHistoryManagerController {

    private final TourHistoryManagerService tourHistoryManagerService;

//    @PostMapping("/tourProduct/registerHistoryManager")
//    public ResponseEntity<TourHistoryManagerResponse> addHistoryManager(
//            @RequestBody TourHistoryManagerRequest tourHistoryManagerRequest
//            ) {
//
//        TourHistoryManagerResponse tourHistoryManagerResponse =
//                tourHistoryManagerService.register( tourHistoryManagerRequest);
//        return ResponseEntity.ok(tourHistoryManagerResponse);
//    }
}
