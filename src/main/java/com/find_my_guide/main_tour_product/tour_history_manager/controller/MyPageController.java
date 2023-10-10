package com.find_my_guide.main_tour_product.tour_history_manager.controller;

import com.find_my_guide.main_tour_product.tour_history_manager.service.TourHistoryManagerService;
import com.find_my_guide.main_tour_product.tour_product.dto.TourProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/my-page")
@RequiredArgsConstructor
public class MyPageController {

    private final TourHistoryManagerService tourHistoryManagerService;

    @GetMapping("/reservation/completed-tours")
    public List<TourProductResponse> getCompletedToursForTourist(final Authentication authentication) {
        return tourHistoryManagerService.getCompletedToursForTourist((String) authentication.getPrincipal());
    }

    @GetMapping("/reservation/upcoming-tours")
    public List<TourProductResponse> getUpcomingToursForTourist(final Authentication authentication) {
        return tourHistoryManagerService.getUpcomingToursForTourist((String) authentication.getPrincipal());
    }
}
