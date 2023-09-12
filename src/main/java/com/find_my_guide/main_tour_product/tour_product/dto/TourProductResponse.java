package com.find_my_guide.main_tour_product.tour_product.dto;

import com.find_my_guide.main_tour_product.available_reservation_date.dto.AvailableDateResponse;
import com.find_my_guide.main_tour_product.tour_product.domain.Languages;
import com.find_my_guide.main_tour_product.tour_product.domain.TourProduct;
import com.find_my_guide.main_tour_product.tour_product_location.dto.TourProductLocationResponse;
import com.find_my_guide.main_tour_product.tour_product_theme.dto.TourProductThemeResponse;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class TourProductResponse {

    private Long id;

    private String title;

    private String content;

    private BigDecimal price;

    private List<TourProductLocationResponse> locations = new ArrayList<>();

    private List<AvailableDateResponse> availableDates;

    private List<Languages> languages = new ArrayList<>();

    private List<TourProductThemeResponse> themeResponses;


    public TourProductResponse(TourProduct tourProduct) {
        this.id = tourProduct.getTourProductId();
        this.title = tourProduct.getTitle().getTitle();
        this.content = tourProduct.getContent().getContent();
        if (tourProduct.getTourProductThemes() != null) {
            this.locations = tourProduct.getTourProductLocations().
                    stream().map(TourProductLocationResponse::new)
                    .collect(Collectors.toList());
        }
        this.languages = tourProduct.getLanguages();
        this.price = BigDecimal.valueOf(tourProduct.getPrice().getPrice().longValue());
        this.availableDates = tourProduct.getAvailableDates().stream()
                .map(AvailableDateResponse::new)
                .collect(Collectors.toList());
        if (tourProduct.getTourProductThemes() != null) {
            this.themeResponses = tourProduct.getTourProductThemes().stream()
                    .map(TourProductThemeResponse::new)
                    .collect(Collectors.toList());
        }
    }
}
