package com.find_my_guide.main_tour_product.tour_history_manager.dto;

import com.find_my_guide.main_member.member.domain.entity.Member;
import com.find_my_guide.main_tour_product.tour_history_manager.domain.TourHistoryManager;
import com.find_my_guide.main_tour_product.tour_product.domain.TourProduct;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class TourHistoryManagerGuideRequest {
    private Long guideId;
    private Long tourProductId;
    private LocalDate tourStartDate;
    private LocalDate tourEndDate;

    public TourHistoryManager toTourHistoryManager(Member guide, TourProduct tourProduct) {
        return TourHistoryManager.builder()
                .tourProduct(tourProduct)
                .guide(guide)
                .tourStartDate(tourStartDate)
                .tourEndDate(tourEndDate)
                .build();
    }
}