package com.find_my_guide.main_tour_product.want_tour_product_theme.dto;


import com.find_my_guide.main_tour_product.want_tour_product.domain.WantTourProduct;
import com.find_my_guide.main_tour_product.want_tour_product_location.dto.WantTourProductLocationResponse;
import com.find_my_guide.main_tour_product.want_tour_product_theme.domain.WantTourProductTheme;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class WantTourProductThemeResponse {

    private Long id;

    private String title;

    public WantTourProductThemeResponse(WantTourProductTheme wantTourProductTheme){
        this.id = wantTourProductTheme.getId();
        this.title = wantTourProductTheme.getTheme().getTitle().getTitle();
    }
}
