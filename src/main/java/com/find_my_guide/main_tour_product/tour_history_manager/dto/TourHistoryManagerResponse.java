package com.find_my_guide.main_tour_product.tour_history_manager.dto;

import com.find_my_guide.main_tour_product.tour_history_manager.domain.TourHistoryManager;
import com.find_my_guide.main_tour_product.tour_product.domain.Images;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import reactor.util.annotation.Nullable;

import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class TourHistoryManagerResponse {
    private Long tourHistoryManagerId;
    private Long guideId;
    private Long touristId;  // 여행객의 ID
    private Long tourProductId;

    private List<String> images = new ArrayList<>();
    @Nullable
    private String tourStartDate;

    @Nullable
    private String tourEndDate;
    @Nullable
    private boolean isCompleted;

    private String tourTitle;

    private String touristEmail;

    public TourHistoryManagerResponse(TourHistoryManager tourHistoryManager) {
        if (tourHistoryManager == null) {
            throw new IllegalArgumentException("TourHistoryManager must not be null");
        }
        if (tourHistoryManager.getId() != null) {
            this.tourHistoryManagerId = tourHistoryManager.getId();
        }

        if (tourHistoryManager.getTourProduct() != null){
            this.tourTitle = tourHistoryManager.getTourProduct().getTitle().getTitle();
        }

        if (tourHistoryManager.getTourist() != null){
            this.touristEmail = tourHistoryManager.getTourist().getEmail();
        }


        if (tourHistoryManager.getTourProduct()!= null ){
            this.images = Optional.ofNullable(tourHistoryManager.getTourProduct().getImages()).orElse(Collections.emptyList())
                    .stream()
                    .map(Images::getImageUrl)
                    .collect(Collectors.toList());
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
