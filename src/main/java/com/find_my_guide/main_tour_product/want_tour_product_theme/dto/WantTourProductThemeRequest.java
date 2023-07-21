package com.find_my_guide.main_tour_product.want_tour_product_theme.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WantTourProductThemeRequest {

    private Long themeId;

    private Long wantTourProductId;
}
