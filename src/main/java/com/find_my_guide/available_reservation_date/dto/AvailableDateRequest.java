package com.find_my_guide.available_reservation_date.dto;

import com.find_my_guide.available_reservation_date.domain.AvailableDate;

import java.time.LocalDate;

public class AvailableDateRequest {

    private LocalDate date;

    public AvailableDate toAvailableDateRequest(){
        return AvailableDate.builder()
                .date(date)
                .build();
    }

}
