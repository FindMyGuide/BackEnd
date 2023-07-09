package com.find_my_guide.main_tour_product.tour_product_theme.dto;

import com.find_my_guide.main_tour_product.tour_product_theme.domain.TourProductTheme;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TourProductThemeResponse {
    private Long id;
    private String title;

    public TourProductThemeResponse(TourProductTheme tourProductTheme) {
        this.id = tourProductTheme.getId();
        this.title = tourProductTheme.getTheme().getTitle().getTitle();
    }
}