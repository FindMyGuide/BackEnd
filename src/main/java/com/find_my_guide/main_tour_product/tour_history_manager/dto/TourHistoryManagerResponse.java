package com.find_my_guide.main_tour_product.tour_history_manager.dto;

import com.find_my_guide.main_tour_product.tour_history_manager.domain.TourHistoryManager;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.format.DateTimeFormatter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class TourHistoryManagerResponse {
    private Long guideId;
    private Long touristId;  // 여행객의 ID
    private Long tourProductId;
    private String tourStartDate;
    private String tourEndDate;
    private boolean isCompleted;

    public TourHistoryManagerResponse(TourHistoryManager tourHistoryManager) {
        this.guideId = tourHistoryManager.getGuide().getIdx();
        this.touristId = tourHistoryManager.getTourist() != null ? tourHistoryManager.getTourist().getIdx() : null;
        this.tourProductId = tourHistoryManager.getTourProduct().getTourProductId();
        this.isCompleted = tourHistoryManager.isCompleted();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.tourStartDate = tourHistoryManager.getTourStartDate() != null ? tourHistoryManager.getTourStartDate().format(formatter) : null;
        this.tourEndDate = tourHistoryManager.getTourEndDate() != null ? tourHistoryManager.getTourEndDate().format(formatter) : null;
    }
}
