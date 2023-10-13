package com.find_my_guide.main_tour_product.tour_product.dto;

import com.find_my_guide.main_tour_product.available_reservation_date.dto.AvailableDateResponse;
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
import java.util.List;
import java.util.stream.Collectors;

@Data
public class TourProductResponse {

    private Long id;

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

    public TourProductResponse(TourProduct tourProduct, List<LocalDate> reservedDates) {
        this.id = tourProduct.getTourProductId();
        this.title = tourProduct.getTitle().getTitle();
        this.content = tourProduct.getContent().getContent();
        this.reservedDates = reservedDates;
        this.locations = tourProduct.getTourProductLocations().
                stream().map(TourProductLocationResponse::new)
                .collect(Collectors.toList());

        if (tourProduct.getHowManyDay() != null) {
            if (tourProduct.getHowManyDay().getNight() != null) {
                this.howManyDay.add(tourProduct.getHowManyDay().getNight());
            }
            if (tourProduct.getHowManyDay().getDay() != null) {
                this.howManyDay.add(tourProduct.getHowManyDay().getDay());
            }
        }

        this.languages = tourProduct.getLanguages();

        this.price = BigDecimal.valueOf(tourProduct.getPrice().getPrice().longValue());

        if (tourProduct.getAvailableDates() != null) {
            this.availableDates = tourProduct.getAvailableDates().stream()
                    .map(AvailableDateResponse::new)
                    .collect(Collectors.toList());
        }
        if (tourProduct.getTourProductThemes() != null) {
            this.themeResponses = tourProduct.getTourProductThemes().stream()
                    .map(TourProductThemeResponse::new)
                    .collect(Collectors.toList());
        }
        if (tourProduct.getImages() != null && !tourProduct.getImages().isEmpty()) {
            this.imageUrls = tourProduct.getImages().stream()
                    .map(Images::getImageUrl)
                    .collect(Collectors.toList());
            this.bestImage = tourProduct.getImages().get(0).getImageUrl();
        }
    }


    public TourProductResponse(TourProduct tourProduct) {
        this.id = tourProduct.getTourProductId();
        this.title = tourProduct.getTitle().getTitle();
        this.content = tourProduct.getContent().getContent();


        this.locations = tourProduct.getTourProductLocations().
                stream().map(TourProductLocationResponse::new)
                .collect(Collectors.toList());

        if (tourProduct.getHowManyDay() != null) {
            if (tourProduct.getHowManyDay().getNight() != null) {
                this.howManyDay.add(tourProduct.getHowManyDay().getNight());
            }
            if (tourProduct.getHowManyDay().getDay() != null) {
                this.howManyDay.add(tourProduct.getHowManyDay().getDay());
            }
        }

        this.languages = tourProduct.getLanguages();

        this.price = BigDecimal.valueOf(tourProduct.getPrice().getPrice().longValue());

        if (tourProduct.getAvailableDates() != null) {
            this.availableDates = tourProduct.getAvailableDates().stream()
                    .map(AvailableDateResponse::new)
                    .collect(Collectors.toList());
        }
        if (tourProduct.getTourProductThemes() != null) {
            this.themeResponses = tourProduct.getTourProductThemes().stream()
                    .map(TourProductThemeResponse::new)
                    .collect(Collectors.toList());
        }
        if (tourProduct.getImages() != null && !tourProduct.getImages().isEmpty()) {
            this.imageUrls = tourProduct.getImages().stream()
                    .map(Images::getImageUrl)
                    .collect(Collectors.toList());
            this.bestImage = tourProduct.getImages().get(0).getImageUrl();
        }
    }
}
