package com.find_my_guide.main_tour_product.tour_history_manager.dto;

import com.find_my_guide.main_tour_product.tour_history_manager.domain.TourHistoryManager;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class TourHistoryManagerResponse {

    private Long tourManagerHistoryId;
    private String tourStartDate;
    private String tourEndDate;
    private String guideLanguage;

    public TourHistoryManagerResponse(TourHistoryManager tourHistoryManager) {
        this.tourManagerHistoryId = tourHistoryManager.getTourManagerHistoryId();
        this.tourStartDate = tourHistoryManager.getTourStartDate();
        this.tourEndDate = tourHistoryManager.getTourEndDate();
        this.guideLanguage = tourHistoryManager.getGuideLanguage();
    }
}
