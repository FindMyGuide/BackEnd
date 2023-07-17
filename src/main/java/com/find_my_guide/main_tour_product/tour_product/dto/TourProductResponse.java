package com.find_my_guide.main_tour_product.tour_product.dto;

import com.find_my_guide.main_tour_product.available_reservation_date.dto.AvailableDateResponse;
import com.find_my_guide.main_tour_product.tour_product.domain.TourProduct;
import com.find_my_guide.main_tour_product.tour_product_theme.dto.TourProductThemeResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TourProductResponse {

    private Long id;

    private String title;

    private String content;

    private BigDecimal price;

    private BigDecimal mapX;

    private BigDecimal mapY;

    private String location;

    private List<AvailableDateResponse> availableDates;

    private List<TourProductThemeResponse> themeResponses;




    public TourProductResponse(TourProduct tourProduct) {
        this.id = tourProduct.getTourProductId();
        this.title = tourProduct.getTitle().getTitle();
        this.content = tourProduct.getContent().getContent();
        this.mapX = tourProduct.getMapX();
        this.mapY = tourProduct.getMapY();
        this.location = tourProduct.getLocation();
        this.price = BigDecimal.valueOf(tourProduct.getPrice().getPrice().longValue());
        this.availableDates =  tourProduct.getAvailableDates().stream()
                .map(AvailableDateResponse::new)
                .collect(Collectors.toList());
        if (tourProduct.getTourProductThemes() != null) {
            this.themeResponses = tourProduct.getTourProductThemes().stream()
                    .map(TourProductThemeResponse::new)
                    .collect(Collectors.toList());
        }
    }
}