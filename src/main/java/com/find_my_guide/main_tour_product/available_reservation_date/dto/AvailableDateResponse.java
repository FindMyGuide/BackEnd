package com.find_my_guide.main_tour_product.available_reservation_date.dto;

import com.find_my_guide.main_tour_product.available_reservation_date.domain.AvailableDate;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDate;

@Data
public class AvailableDateResponse {

    private LocalDate date;


    public AvailableDateResponse(AvailableDate availableDate) {
        this.date = availableDate.getDate();
    }
}
