package com.find_my_guide.main_tour_product.want_reservation_date.dto;

import com.find_my_guide.main_tour_product.want_reservation_date.domain.WantReservationDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class WantReservationDateRequest {

    private LocalDate date;

    public WantReservationDateRequest(String date) {
        this.date = LocalDate.parse(date);
    }

    public WantReservationDate toWantReservationDate() {
        return WantReservationDate.builder()
                .date(date)
                .build();
    }
}
