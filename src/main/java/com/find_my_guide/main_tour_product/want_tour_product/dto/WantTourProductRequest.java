package com.find_my_guide.main_tour_product.want_tour_product.dto;

import com.find_my_guide.main_member.member.domain.entity.Gender;
import com.find_my_guide.main_tour_product.common.validation_field.Content;
import com.find_my_guide.main_tour_product.common.validation_field.Title;
import com.find_my_guide.main_tour_product.location.dto.LocationRequest;
import com.find_my_guide.main_tour_product.location.dto.WantTourLocationRequest;
import com.find_my_guide.main_tour_product.tour_product.domain.Price;
import com.find_my_guide.main_tour_product.want_tour_product.domain.Vehicle;
import com.find_my_guide.main_tour_product.want_tour_product.domain.WantTourProduct;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class WantTourProductRequest {


    private Long wantTourProductId;

    @NotNull
    private String title;

    @NotNull
    private String content;

    @NotNull(message = "가격은 반드시 입력해야 합니다.")
    @Positive(message = "가격은 0보다 커야 합니다.")
    private Long price;

    private int totalPeople;

    private String vehicle;

    private List<WantTourLocationRequest> location = new ArrayList<>();

    private List<Long> themeIds = new ArrayList<>();

    private List<LocalDate> wantDates = new ArrayList<>();


    public WantTourProduct toWantTourProduct() {
        return WantTourProduct.builder()
                .wantTourProductId(this.wantTourProductId)
                .title(new Title(this.title))
                .content(new Content(this.content))
                .totalPeople(this.totalPeople)
                .price(new Price(this.price))
                .reserved(false)
                .vehicle(Vehicle.fromString(this.vehicle))
                .build();
    }
}
