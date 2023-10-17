package com.find_my_guide.main_tour_product.tour_history_manager.dto;

import com.find_my_guide.main_tour_product.tour_history_manager.domain.TourHistoryManager;
import com.find_my_guide.main_tour_product.tour_product.domain.Images;
import lombok.Data;
import reactor.util.annotation.Nullable;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Data
public class WantTourHistoryManagerResponse {

    private Long tourHistoryManagerId;
    private Long guideId;
    private Long touristId;  // 여행객의 ID
    private Long wantTourProductId;

    @Nullable
    private String tourStartDate;

    @Nullable
    private String tourEndDate;


    private String wantTourTitle;

    public WantTourHistoryManagerResponse (TourHistoryManager tourHistoryManager){
        if (tourHistoryManager == null) {
            throw new IllegalArgumentException("TourHistoryManager must not be null");
        }

        if (tourHistoryManager.getWantTourProduct() != null){
            this.wantTourProductId = tourHistoryManager.getWantTourProduct().getWantTourProductId();
        }

        if (tourHistoryManager.getId() != null) {
            this.tourHistoryManagerId = tourHistoryManager.getId();
        }

        if (tourHistoryManager.getTourProduct() != null){
            this.wantTourTitle = tourHistoryManager.getWantTourProduct().getTitle().getTitle();
        }

        if (tourHistoryManager.getGuide() != null) {
            this.guideId = tourHistoryManager.getGuide().getIdx();
        }

        if (tourHistoryManager.getTourist() != null) {
            this.touristId = tourHistoryManager.getTourist().getIdx();
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        if (tourHistoryManager.getTourStartDate() != null) {
            this.tourStartDate = tourHistoryManager.getTourStartDate().format(formatter);
        }

        if (tourHistoryManager.getTourEndDate() != null) {
            this.tourEndDate = tourHistoryManager.getTourEndDate().format(formatter);
        }
    }
}
