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
    private String tourStartDate;
    private String tourEndDate;
    private String guideLanguage;

    public TourHistoryManager toTourHistoryManager() {
        return TourHistoryManager.builder()
                .tourManagerHistoryId(tourManagerHistoryId)
                .tourStartDate(tourStartDate)
                .tourEndDate(tourEndDate)
                .guideLanguage(guideLanguage)
                .build();
    }
}
