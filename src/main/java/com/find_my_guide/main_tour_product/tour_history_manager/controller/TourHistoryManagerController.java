package com.find_my_guide.main_tour_product.tour_history_manager.controller;

import com.find_my_guide.main_tour_product.tour_history_manager.dto.TourHistoryManagerRequest;
import com.find_my_guide.main_tour_product.tour_history_manager.dto.TourHistoryManagerResponse;
import com.find_my_guide.main_tour_product.tour_history_manager.service.TourHistoryManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TourHistoryManagerController {

    private final TourHistoryManagerService tourHistoryManagerService;

    @PostMapping("/tourProduct/registerHistoryManager/{tourProductId}/{memberId}")
    public ResponseEntity<TourHistoryManagerResponse> addHistoryManager(
            @PathVariable Long memberId,
            @PathVariable Long tourProductId,
            @RequestBody TourHistoryManagerRequest tourHistoryManagerRequest
            ) {
        TourHistoryManagerResponse tourHistoryManagerResponse =
                tourHistoryManagerService.register(memberId, tourProductId, tourHistoryManagerRequest);
        return ResponseEntity.ok(tourHistoryManagerResponse);
    }
}
