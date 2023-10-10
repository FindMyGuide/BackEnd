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

    private List<TourProductLocationResponse> locations;

    private List<AvailableDateResponse> availableDates;

    private List<String> howManyDay = new ArrayList<>();

    private List<Languages> languages;

    private List<TourProductThemeResponse> themeResponses;


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
    }
}
