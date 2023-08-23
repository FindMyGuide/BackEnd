package com.find_my_guide.main_tour_product.tour_product.dto;

import com.find_my_guide.main_tour_product.common.validation_field.Content;
import com.find_my_guide.main_tour_product.common.validation_field.Title;
import com.find_my_guide.main_tour_product.tour_product.domain.Price;
import com.find_my_guide.main_tour_product.tour_product.domain.TourProduct;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TourProductRequest {

    private Long tourProductId;

    @NotNull
    private String title;

    @NotNull
    private String content;

    private BigDecimal mapX;

    private BigDecimal mapY;

    private String location;


    @NotNull(message = "가격은 반드시 입력해야 합니다.")
    @Positive(message = "가격은 0보다 커야 합니다.")
    private Long price;

    private List<Long> themeIds; // 각 테마의 ID를 포함하는 리스트

    private List<LocalDate> availableDates; // 사용 가능한 날짜를 포함하는 리스트

    public TourProduct toTourProduct() {
        return TourProduct.builder()
                .tourProductId(tourProductId)
                .title(new Title(title))
                .content(new Content(content))
                .price(new Price(price))
                .mapX(this.mapX)
                .mapY(this.mapY)
                .location(this.location)
                .build();
    }


}
