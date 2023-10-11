package com.find_my_guide.main_tour_product.want_tour_product.dto;

import com.find_my_guide.main_tour_product.location.dto.LocationRequest;
import com.find_my_guide.main_tour_product.want_tour_product.domain.Vehicle;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
public class UpdateWantTourProductRequest {


    private String title;

    private String content;

    @Positive(message = "가격은 0보다 커야 합니다.")
    private Long price;

    private String gender;

    private int totalPeople;

    private Vehicle vehicle;

    private List<LocationRequest> location = new ArrayList<>();

    private List<Long> themeIds;

    private List<LocalDate> wantDates;
}
