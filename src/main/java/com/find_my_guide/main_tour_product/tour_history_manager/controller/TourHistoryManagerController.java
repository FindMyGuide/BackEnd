package com.find_my_guide.main_tour_product.tour_history_manager.controller;

import com.find_my_guide.main_tour_product.tour_history_manager.service.TourHistoryManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TourHistoryManagerController {

    private final TourHistoryManagerService tourHistoryManagerService;


}
