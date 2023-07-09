package com.find_my_guide.available_reservation_date.dto;

import com.find_my_guide.available_reservation_date.domain.AvailableDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AvailableDateRequest {

    private LocalDate date;

    public AvailableDate toAvailableDateRequest(){
        return AvailableDate.builder()
                .date(date)
                .build();
    }

}
