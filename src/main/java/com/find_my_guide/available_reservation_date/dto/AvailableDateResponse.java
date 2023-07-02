package com.find_my_guide.available_reservation_date.dto;

import com.find_my_guide.available_reservation_date.domain.AvailableDate;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class AvailableDateResponse {

    private Long id;

    private LocalDate date;

    private Long tourProductId;

    public AvailableDateResponse(AvailableDate availableDate) {
        this.id = availableDate.getId();
        this.date = availableDate.getDate();
        this.tourProductId = availableDate.getTourProduct().getTourProductId();
    }
}
