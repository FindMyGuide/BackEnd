package com.find_my_guide.main_tour_product.available_reservation_date.dto;

import com.find_my_guide.main_tour_product.available_reservation_date.domain.AvailableDate;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class AvailableDateResponse {

    private LocalDate date;

    private Long tourProductId;

    public AvailableDateResponse(AvailableDate availableDate) {
        this.date = availableDate.getDate();
        this.tourProductId = availableDate.getTourProduct().getTourProductId();
    }
}
