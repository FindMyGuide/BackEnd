package com.find_my_guide.main_tour_product.want_reservation_date.dto;

import com.find_my_guide.main_tour_product.want_reservation_date.domain.WantReservationDate;

import java.time.LocalDate;

public class WantReservationDateResponse {
    private LocalDate date;
    private Long wantTourProductId;

    public WantReservationDateResponse(WantReservationDate wantReservationDate) {
        this.date = wantReservationDate.getDate();
        this.wantTourProductId = wantReservationDate.getWantTourProduct().getWantTourProductId();
    }
}
