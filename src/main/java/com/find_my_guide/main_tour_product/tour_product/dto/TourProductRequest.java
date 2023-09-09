package com.find_my_guide.main_tour_product.tour_product.dto;

import com.find_my_guide.main_tour_product.common.validation_field.Content;
import com.find_my_guide.main_tour_product.common.validation_field.Title;
import com.find_my_guide.main_tour_product.tour_product.domain.Coordinates;
import com.find_my_guide.main_tour_product.tour_product.domain.Languages;
import com.find_my_guide.main_tour_product.tour_product.domain.Price;
import com.find_my_guide.main_tour_product.tour_product.domain.TourProduct;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TourProductRequest {

    @NotNull
    private String title;

    @NotNull
    private String content;

    private List<BigDecimal> coordinates = new ArrayList<>();

    private List<Languages> languages = new ArrayList<>();



    private String location;

    @NotNull(message = "가격은 반드시 입력해야 합니다.")
    @Positive(message = "가격은 0보다 커야 합니다.")
    private Long price;

    private List<Long> themeIds; // 각 테마의 ID를 포함하는 리스트

    private List<LocalDate> availableDates; // 사용 가능한 날짜를 포함하는 리스트

    public TourProduct toTourProduct() {
        return TourProduct.builder()
                .title(new Title(title))
                .content(new Content(content))
                .price(new Price(price))
                .coordinates(new Coordinates(this.coordinates.get(0),this.coordinates.get(1)))
                .languages(this.languages)
                .location(this.location)
                .build();
    }


}
