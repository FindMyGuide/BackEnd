package com.find_my_guide.main_tour_product.tour_product.dto;

import com.find_my_guide.main_member.member.domain.entity.Member;
import com.find_my_guide.main_tour_product.common.validation_field.Content;
import com.find_my_guide.main_tour_product.common.validation_field.Title;
import com.find_my_guide.main_tour_product.tour_history_manager.domain.TourHistoryManager;
import com.find_my_guide.main_tour_product.tour_product.domain.Images;
import com.find_my_guide.main_tour_product.tour_product.domain.TourProduct;
import com.find_my_guide.main_tour_product.tour_product_location.dto.TourProductLocationResponse;
import lombok.Data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Data
public class TourProductSearchResponse {

    private Long id;

    private String title;

    private String content;

    private List<TourProductLocationResponse> locations;

    private Long guideId;

    private String guideName;

    private List<String> imageUrls = new ArrayList<>();

    private String bestImage;

    public TourProductSearchResponse(TourProduct tourProduct) {

        this.id = Optional.ofNullable(tourProduct.getTourProductId()).orElse(0L);
        this.title = Optional.ofNullable(tourProduct.getTitle())
                .map(Title::getTitle)
                .orElse("");


        List<String> images = Optional.ofNullable(tourProduct.getImages()).orElse(Collections.emptyList())
                .stream()
                .map(Images::getImageUrl)
                .collect(Collectors.toList());

        if (!images.isEmpty()) {
            this.imageUrls = images;
            this.bestImage = images.get(0);
        }

        this.content = Optional.ofNullable(tourProduct.getContent())
                .map(Content::getContent)
                .orElse("");

        this.locations = Optional.ofNullable(tourProduct.getTourProductLocations())
                .orElse(Collections.emptyList())
                .stream()
                .map(TourProductLocationResponse::new)
                .collect(Collectors.toList());

        TourHistoryManager historyManager = tourProduct.getTourHistoryManagers().stream()
                .filter(thm -> thm.getGuide() != null)
                .findFirst()
                .orElse(null);

        if (historyManager != null) {
            Member guide = historyManager.getGuide();
            this.guideId = guide.getIdx();

            this.guideName = guide.getName() != null ? guide.getName() : "";
        }


    }
}
