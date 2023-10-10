package com.find_my_guide.main_tour_product.tour_history_manager.controller;

import com.find_my_guide.main_tour_product.tour_history_manager.service.TourHistoryManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@EnableScheduling
public class IsCompletedScheduling {

    private final TourHistoryManagerService tourHistoryManagerService;

    @Scheduled(cron = "0 0 0 * * ?")
    public void markTourAsCompleted() {
        tourHistoryManagerService.markToursAsCompleted();
    }
}
