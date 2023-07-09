package com.find_my_guide.tour_product_theme.dto;

import com.find_my_guide.tour_product_theme.domain.TourProductTheme;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TourProductThemeRequest {

    private Long themeId;

    private Long tourProductId;

}
