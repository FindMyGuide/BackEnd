package com.find_my_guide.available_reservation_date.domain;

import com.find_my_guide.tour_product.domain.TourProduct;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

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


    public void addAvailableDate(TourProduct tourProduct) {
        this.tourProduct = tourProduct;
        this.tourProduct.getAvailableDates().add(this);

    }

    public void setTourProduct(TourProduct tourProduct){
        this.tourProduct = tourProduct;
        if (!tourProduct.getAvailableDates().contains(this)) {
            tourProduct.addAvailableDate(this);
        }
    }

}
