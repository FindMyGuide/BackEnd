package com.find_my_guide.main_tour_product.available_reservation_date.domain;

import com.find_my_guide.main_tour_product.tour_product.domain.TourProduct;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;

@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
public class AvailableDate {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "tourProduct_id")
    private TourProduct tourProduct;

    private boolean isReserved;  // 예약 상태 표시


    public void addAvailableDate(TourProduct tourProduct) {
        this.tourProduct = tourProduct;
        if (this.tourProduct.getAvailableDates() == null) {
            this.tourProduct.setAvailableDates(new ArrayList<>());
        }
        this.tourProduct.getAvailableDates().add(this);
    }

    public void setReserved(Boolean isReserved){
        this.isReserved = isReserved;
    }




}
