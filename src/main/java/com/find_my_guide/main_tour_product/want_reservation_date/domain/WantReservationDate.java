package com.find_my_guide.main_tour_product.want_reservation_date.domain;

import com.find_my_guide.main_tour_product.want_tour_product.domain.WantTourProduct;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
public class WantReservationDate {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "want_tour_product_Id")
    private WantTourProduct wantTourProduct;

    public void addWantDate(WantTourProduct wantTourProduct) {
        this.wantTourProduct = wantTourProduct;

    }
}
