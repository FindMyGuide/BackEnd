package com.find_my_guide.main_tour_product.want_reservation_date.repository;

import com.find_my_guide.main_tour_product.want_reservation_date.domain.WantReservationDate;
import com.find_my_guide.main_tour_product.want_tour_product.domain.WantTourProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WantReservationDateRepository extends JpaRepository<WantReservationDate, Long> {

    WantReservationDate findByWantTourProductAndId(WantTourProduct wantTourProduct, Long id);
}
