package com.find_my_guide.main_tour_product.want_tour_product.domain;

import com.find_my_guide.main_tour_product.common.validation_field.Content;
import com.find_my_guide.main_tour_product.common.validation_field.Title;
import com.find_my_guide.main_tour_product.want_reservation_date.domain.WantReservationDate;
import com.find_my_guide.main_tour_product.want_tour_product_location.domain.WantTourProductLocation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WantTourProduct {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long wantTourProductId;

    private LocalDate createAt;

    @Embedded
    private Title title;

    @Embedded
    private Content content;

    @OneToMany(mappedBy = "wantTourProduct")
    private List<WantTourProductLocation> wantTourProductLocations = new ArrayList<>();

    @OneToMany(mappedBy = "wantTourProduct")
    private List<WantReservationDate> wantReservationDates = new ArrayList<>();


    public void setWantReservationDates(List<WantReservationDate> wantReservationDates) {
        this.wantReservationDates = wantReservationDates;
    }

    public List<WantReservationDate> wantReservationDates() {
        if (this.wantReservationDates == null) {
            this.wantReservationDates = new ArrayList<>();
        }
        return this.wantReservationDates;
    }

   public void update(Title title, Content content) {
       this.title = title;
       this.content = content;
   }




}
