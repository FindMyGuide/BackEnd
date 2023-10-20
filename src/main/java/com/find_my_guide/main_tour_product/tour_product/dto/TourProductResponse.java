package com.find_my_guide.main_tour_product.tour_product.dto;

import com.find_my_guide.main_member.member.domain.entity.Member;
import com.find_my_guide.main_tour_product.available_reservation_date.dto.AvailableDateResponse;
import com.find_my_guide.main_tour_product.common.validation_field.Content;
import com.find_my_guide.main_tour_product.common.validation_field.Title;
import com.find_my_guide.main_tour_product.tour_history_manager.domain.TourHistoryManager;
import com.find_my_guide.main_tour_product.tour_product.domain.Images;
import com.find_my_guide.main_tour_product.tour_product.domain.Languages;
import com.find_my_guide.main_tour_product.tour_product.domain.TourProduct;
import com.find_my_guide.main_tour_product.tour_product_location.dto.TourProductLocationResponse;
import com.find_my_guide.main_tour_product.tour_product_theme.dto.TourProductThemeResponse;
import lombok.Data;
import reactor.util.annotation.Nullable;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Data
public class TourProductResponse {

    private Long tourProductId;

    private String title;

    private String content;

    private BigDecimal price;

    @Nullable
    private Long likes;

    private boolean isLikeExist;

    private List<TourProductLocationResponse> locations;

    private List<LocalDate> reservedDates = new ArrayList<>();

    private List<AvailableDateResponse> availableDates = new ArrayList<>();

    private List<String> howManyDay = new ArrayList<>();

    private List<Languages> languages;

    private List<TourProductThemeResponse> themeResponses;

    private List<String> imageUrls = new ArrayList<>();

    private String bestImage;

    private String guideNickName;

    private String guideName;

    public TourProductResponse(TourProduct tourProduct) {
        if (tourProduct == null) {
            return;
        }

        TourHistoryManager history = tourProduct.getTourHistoryManagers().stream().findFirst().orElse(null);
        if(history != null) {
            Member guide = history.getGuide();
            if(guide != null) {
                this.guideNickName = guide.getNickname();
                this.guideName = guide.getName();
            }
        }

        this.tourProductId = tourProduct.getTourProductId();
        this.title = Optional.ofNullable(tourProduct.getTitle()).map(Title::getTitle).orElse(null);
        this.likes = Optional.ofNullable(tourProduct.getTourProductLikes())
                .stream().count();
        this.content = Optional.ofNullable(tourProduct.getContent()).map(Content::getContent).orElse(null);
        this.locations = Optional.ofNullable(tourProduct.getTourProductLocations())
                .orElse(Collections.emptyList())
                .stream()
                .map(TourProductLocationResponse::new)
                .collect(Collectors.toList());

        Optional.ofNullable(tourProduct.getHowManyDay()).ifPresent(h -> {
            this.howManyDay.add(h.getNight());
            this.howManyDay.add(h.getDay());
        });

        this.languages = Optional.ofNullable(tourProduct.getLanguages()).orElse(Collections.emptyList());
        this.price = Optional.ofNullable(tourProduct.getPrice()).map(p -> BigDecimal.valueOf(p.getPrice().longValue())).orElse(BigDecimal.ZERO);
        this.availableDates = Optional.ofNullable(tourProduct.getAvailableDates())
                .orElse(Collections.emptyList())
                .stream()
                .map(AvailableDateResponse::new)
                .collect(Collectors.toList());

        this.themeResponses = Optional.ofNullable(tourProduct.getTourProductThemes())
                .orElse(Collections.emptyList())
                .stream()
                .map(TourProductThemeResponse::new)
                .collect(Collectors.toList());

        List<String> images = Optional.ofNullable(tourProduct.getImages()).orElse(Collections.emptyList())
                .stream()
                .map(Images::getImageUrl)
                .collect(Collectors.toList());

        if (!images.isEmpty()) {
            this.imageUrls = images;
            this.bestImage = images.get(0);
        }
    }
}
