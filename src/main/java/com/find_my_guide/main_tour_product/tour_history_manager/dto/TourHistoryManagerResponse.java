package com.find_my_guide.main_tour_product.tour_history_manager.dto;

import com.find_my_guide.main_tour_product.tour_history_manager.domain.TourHistoryManager;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import reactor.util.annotation.Nullable;

import java.time.format.DateTimeFormatter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class TourHistoryManagerResponse {
    private Long guideId;
    private Long touristId;  // 여행객의 ID
    private Long tourProductId;

    @Nullable
    private String tourStartDate;

    @Nullable
    private String tourEndDate;
    @Nullable
    private boolean isCompleted;

    public TourHistoryManagerResponse(TourHistoryManager tourHistoryManager) {
        if (tourHistoryManager == null) {
            throw new IllegalArgumentException("TourHistoryManager must not be null");
        }

        if (tourHistoryManager.getGuide() != null) {
            this.guideId = tourHistoryManager.getGuide().getIdx();
        }

        if (tourHistoryManager.getTourist() != null) {
            this.touristId = tourHistoryManager.getTourist().getIdx();
        }

        if (tourHistoryManager.getTourProduct() != null) {
            this.tourProductId = tourHistoryManager.getTourProduct().getTourProductId();
        }

        this.isCompleted = tourHistoryManager.getIsCompleted();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        if (tourHistoryManager.getTourStartDate() != null) {
            this.tourStartDate = tourHistoryManager.getTourStartDate().format(formatter);
        }

        if (tourHistoryManager.getTourEndDate() != null) {
            this.tourEndDate = tourHistoryManager.getTourEndDate().format(formatter);
        }
    }

}
