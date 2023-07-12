package com.find_my_guide.main_tour_product.tour_history_manager.dto;

import com.find_my_guide.main_tour_product.tour_history_manager.domain.TourHistoryManager;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class TourHistoryManagerRequest {

    private Long tourManagerHistoryId;

    public TourHistoryManager toTourHistoryManager() {
        return TourHistoryManager.builder()
                .build();
    }
}
