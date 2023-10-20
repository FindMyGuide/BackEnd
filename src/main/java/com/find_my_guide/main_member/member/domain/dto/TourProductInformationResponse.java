package com.find_my_guide.main_member.member.domain.dto;

import com.find_my_guide.main_tour_product.common.validation_field.Title;
import com.find_my_guide.main_tour_product.tour_product.domain.Languages;
import com.find_my_guide.main_tour_product.tour_product.domain.TourProduct;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Data
public class TourProductInformationResponse {

    private String title;

    private BigDecimal price;

    private List<Languages> languages;

    public TourProductInformationResponse(TourProduct tourProduct){
        this.title = Optional.ofNullable(tourProduct.getTitle()).map(Title::getTitle).orElse(null);
        this.languages = Optional.ofNullable(tourProduct.getLanguages()).orElse(Collections.emptyList());
        this.price = Optional.ofNullable(tourProduct.getPrice()).map(p -> BigDecimal.valueOf(p.getPrice().longValue())).orElse(BigDecimal.ZERO);


    }
}
