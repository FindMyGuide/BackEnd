package com.find_my_guide.main_tour_product.tour_history_manager.dto;

import com.find_my_guide.main_tour_product.tour_history_manager.domain.TourHistoryManager;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class TourHistoryManagerResponse {
    private Long guideId;
    private Long memberId;
    private Long tourProductId;
    private String tourStartDate;
    private String tourEndDate;

    public TourHistoryManagerResponse(TourHistoryManager tourHistoryManager) {
        this.guideId = tourHistoryManager.getGuideId();
        this.memberId = tourHistoryManager.getMember().getIdx();
        this.tourProductId = tourHistoryManager.getTourProduct().getTourProductId();
    }

}
