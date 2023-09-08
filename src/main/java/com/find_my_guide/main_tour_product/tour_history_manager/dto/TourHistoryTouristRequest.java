package com.find_my_guide.main_tour_product.tour_history_manager.dto;

import com.find_my_guide.main_tour_product.tour_history_manager.domain.TourHistoryManager;
import lombok.Data;

import java.time.LocalDate;

@Data
public class TourHistoryTouristRequest {
    private String email;

    private Long productId;

    private LocalDate startDate;

    private LocalDate endDate;

}
