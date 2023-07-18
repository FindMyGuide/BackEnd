package com.find_my_guide.main_tour_product.want_tour_product_location.repository;

import com.find_my_guide.main_tour_product.want_tour_product_location.domain.WantTourProductLocation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WantTourProductLocationRepository extends JpaRepository<WantTourProductLocation, Long> {
}
